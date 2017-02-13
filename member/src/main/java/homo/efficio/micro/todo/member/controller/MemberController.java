package homo.efficio.micro.todo.member.controller;

import homo.efficio.micro.todo.member.dto.MemberDto;
import homo.efficio.micro.todo.member.exception.MemberNotFoundException;
import homo.efficio.micro.todo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-16.
 */
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    public MemberController(
            MemberService memberService
    ) {
        this.memberService = memberService;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<MemberDto> save(@RequestBody @Valid MemberDto memberDto,
                                          BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) throw new BindException(bindingResult);

        return ResponseEntity.ok(memberService.save(memberDto));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MemberDto> findMember(@RequestParam("email") String email,
                                                @RequestParam("userName") String userName) {

        MemberDto memberDto;
        if (Objects.nonNull(email)) {
            memberDto = memberService.findByEmail(email);
        } else if (Objects.nonNull(userName)) {
            memberDto = memberService.findByUserName(userName);
        } else {
            throw new MemberNotFoundException();
        }

        return ResponseEntity.ok(memberDto);
    }

    private MemberService memberService;
}
