package homo.efficio.toy.member.service;

import homo.efficio.toy.member.dto.MemberDto;
import homo.efficio.toy.member.exception.MemberNotFoundException;
import homo.efficio.toy.member.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService service;

    private MemberDto memberDto;
    private String userName;
    private String email;

    @Before
    public void setup() {
        memberDto = new MemberDto();
        userName = "homo.efficio";
        email = "homo.efficio@gmail.com";
        memberDto.setUserName(userName);
        memberDto.setEmail(email);
    }

    @Test
    public void 회원등록() throws Exception {

        MemberDto persistedMember = service.save(memberDto);

        assertThat(persistedMember.getEmail()).isEqualTo(memberDto.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(memberDto.getUserName());
    }

    @Test
    public void 회원조회_email() throws Exception {

        service.save(memberDto);

        MemberDto persistedMember = service.findByEmail(email);

        assertThat(persistedMember.getEmail()).isEqualTo(memberDto.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(memberDto.getUserName());
    }

    @Test
    public void 회원조회_userName() throws Exception {

        service.save(memberDto);

        MemberDto persistedMember = service.findByUserName(userName);

        assertThat(persistedMember.getEmail()).isEqualTo(memberDto.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(memberDto.getUserName());
    }

    @Test
    public void 회원삭제() throws Exception {

        service.save(memberDto);

        MemberDto persistedMember = service.findByUserName(userName);

        assertThat(persistedMember.getEmail()).isEqualTo(memberDto.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(memberDto.getUserName());
    }

    @Test(expected = MemberNotFoundException.class)
    public void 존재하지_않는_회원삭제() throws Exception {

        MemberDto memberDto = new MemberDto();
        memberDto.setId(-1L);
        memberDto.setUserName("nonExist");
        memberDto.setEmail("nonExist@non.exist");

        service.delete(memberDto);
    }
}
