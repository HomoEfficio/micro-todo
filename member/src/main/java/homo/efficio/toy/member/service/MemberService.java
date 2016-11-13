package homo.efficio.toy.member.service;

import homo.efficio.toy.member.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hanmomhanda on 2016-11-13.
 */
public interface MemberService {
    MemberDto save(MemberDto memberDto);

    @Transactional(readOnly = true)
    MemberDto findByUserName(String userName);

    @Transactional(readOnly = true)
    MemberDto findByEmail(String email);
}
