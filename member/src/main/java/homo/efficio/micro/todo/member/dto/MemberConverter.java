package homo.efficio.micro.todo.member.dto;

import homo.efficio.micro.todo.member.domain.Member;
import homo.efficio.micro.todo.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-11-13.
 */
@Component
public class MemberConverter {

    public Member getMemberFrom(MemberDto i) {

        return new Member(
                i.getId(),
                i.getUserName(),
                i.getEmail(),
                i.getStatus()
        );
    }

    public MemberDto getMemberDtoFrom(Member i) {

        if (Objects.isNull(i)) throw new MemberNotFoundException();

        MemberDto o = new MemberDto();
        o.setId(i.getId());
        o.setUserName(i.getUserName());
        o.setEmail(i.getEmail());
        o.setStatus(i.getStatus());
        return o;
    }

    public List<MemberDto> getMemberDtosFrom(List<Member> members) {

        if (members.isEmpty())
            return Collections.emptyList();

        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member i: members) {
            memberDtos.add(getMemberDtoFrom(i));
        }
        return memberDtos;
    }
}
