package homo.efficio.toy.member.dto;

import homo.efficio.toy.member.domain.Member;
import homo.efficio.toy.member.exception.MemberNotFoundException;

import java.util.Objects;

/**
 * Created by hanmomhanda on 2016-11-13.
 */
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
}
