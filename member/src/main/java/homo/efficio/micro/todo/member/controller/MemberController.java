package homo.efficio.micro.todo.member.controller;

import homo.efficio.micro.todo.member.dto.MemberDto;
import homo.efficio.micro.todo.member.exception.MemberNotFoundException;
import homo.efficio.micro.todo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Objects;
import java.util.Optional;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-16.
 */
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<MemberDto> save(@RequestBody @Valid MemberDto memberDto,
                                          BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) throw new BindException(bindingResult);

        return ResponseEntity.ok(this.memberService.save(memberDto));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MemberDto> findMember(@RequestParam(value = "email", required = false) String email,
                                                @RequestParam("userName") Optional<String> userName) {

        MemberDto memberDto;
        if (!StringUtils.isEmpty(email)) {
            memberDto = this.memberService.findByEmail(email);
            return ResponseEntity.ok(memberDto);
        }
        // Optional을 시험삼아 써봤으나 required = false 가 더 깔끔한 듯
        if (userName.isPresent() && !StringUtils.isEmpty(userName.get())) {
            memberDto = this.memberService.findByUserName(userName.get());
            return ResponseEntity.ok(memberDto);
        }

        throw new IllegalArgumentException("이메일이나 사용자이름 중의 하나는 있어야 합니다.");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestBody @Valid MemberDto memberDto,
                                 BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) throw new BindException(bindingResult);
        Objects.requireNonNull(memberDto);
        this.memberService.delete(memberDto);
        return ResponseEntity.ok(memberDto);
    }

    private MemberService memberService;
}
