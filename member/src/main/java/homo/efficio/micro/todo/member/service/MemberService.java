package homo.efficio.micro.todo.member.service;

import homo.efficio.micro.todo.member.dto.MemberDto;
import homo.efficio.micro.todo.member.etc.code.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-13.
 */
public interface MemberService {
    MemberDto save(MemberDto memberDto);

    @Transactional(readOnly = true)
    MemberDto findByUserName(String userName);

    @Transactional(readOnly = true)
    MemberDto findByEmail(String email);

    @Transactional
    void delete(MemberDto memberDto);

    @Transactional(readOnly = true)
    List<MemberDto> findAll();

    @Transactional(readOnly = true)
    List<MemberDto> findAllBy(Status status);
}
