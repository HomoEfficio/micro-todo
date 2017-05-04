package homo.efficio.micro.todo.member.service;

import homo.efficio.micro.todo.member.dto.MemberDto;
import homo.efficio.micro.todo.member.etc.code.Status;
import homo.efficio.micro.todo.member.exception.MemberNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional  // 테스트 클래스에 @Transactional 붙여주면 테스트 용으로 insert 후 자동으로 rollback 해줌
public class MemberServiceTest {

    @Autowired
    private MemberService service;

    private MemberDto memberDto;

    @Before
    public void setup() {
        this.memberDto = new MemberDto();
        this.memberDto.setUserName("homo.efficio");
        this.memberDto.setEmail("homo.efficio@gmail.com");
        this.memberDto.setStatus(Status.ACTIVE);
    }

    @Test
    public void 회원등록() throws Exception {

        MemberDto persistedMember = service.save(this.memberDto);

        assertThat(persistedMember.getEmail()).isEqualTo(this.memberDto.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(this.memberDto.getUserName());
    }

    @Test
    public void 회원조회_email() throws Exception {

        service.save(this.memberDto);

        MemberDto persistedMember = service.findByEmail(this.memberDto.getEmail());

        assertThat(persistedMember.getEmail()).isEqualTo(this.memberDto.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(this.memberDto.getUserName());
    }

    @Test
    public void 회원조회_userName() throws Exception {

        service.save(this.memberDto);

        MemberDto persistedMember = service.findByUserName(this.memberDto.getUserName());

        assertThat(persistedMember.getEmail()).isEqualTo(this.memberDto.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(this.memberDto.getUserName());
    }

    @Test
    public void 전체회원조회() throws Exception {

        service.save(this.memberDto);
        MemberDto memberDto1 = new MemberDto(
                "hanmomhanda",
                "hanmomhanda@gmail.com",
                Status.ACTIVE
        );
        service.save(memberDto1);

        List<MemberDto> allMemberDtos = service.findAll();

        assertThat(allMemberDtos.size()).isEqualTo(2);
    }

    @Test
    public void Status별_회원조회() throws Exception {

        service.save(this.memberDto);
        MemberDto memberDto1 = new MemberDto(
                "hanmomhanda",
                "hanmomhanda@gmail.com",
                Status.ACTIVE
        );
        service.save(memberDto1);
        MemberDto memberDto2 = new MemberDto(
                "onetouch",
                "onetouch@gmail.com",
                Status.INACTIVE
        );
        service.save(memberDto2);
        MemberDto memberDto3 = new MemberDto(
                "omwomw",
                "omwomw@gmail.com",
                Status.WITHDRAWN
        );
        service.save(memberDto3);

        List<MemberDto> activeMembers = service.findAllBy(Status.ACTIVE);

        assertThat(activeMembers.size()).isEqualTo(2);

        List<MemberDto> inactiveMembers = service.findAllBy(Status.INACTIVE);

        assertThat(inactiveMembers.size()).isEqualTo(1);

        List<MemberDto> withdrawnMembers = service.findAllBy(Status.WITHDRAWN);

        assertThat(withdrawnMembers.size()).isEqualTo(1);

    }

    @Test(expected = MemberNotFoundException.class)
    public void 회원삭제() throws Exception {

        service.save(this.memberDto);

        MemberDto persistedMember = service.findByEmail(this.memberDto.getEmail());

        assertThat(persistedMember.getEmail()).isEqualTo(this.memberDto.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(this.memberDto.getUserName());

        service.delete(this.memberDto);

        MemberDto persistedMember1 = service.findByEmail(this.memberDto.getEmail());

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
