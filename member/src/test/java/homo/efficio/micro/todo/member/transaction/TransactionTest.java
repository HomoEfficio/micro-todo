package homo.efficio.micro.todo.member.transaction;

import homo.efficio.micro.todo.member.dto.MemberDto;
import homo.efficio.micro.todo.member.etc.code.Status;
import homo.efficio.micro.todo.member.repository.MemberRepository;
import homo.efficio.micro.todo.member.service.MemberService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-01-24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository repository;

    @Test
    public void t00_회원초기화() {
        repository.deleteAll();
        assertThat(memberService.findAll().size()).isEqualTo(0);
    }

    @Test
    public void t01_회원1명저장() throws Exception {
        MemberDto memberDto1 = new MemberDto();
        memberDto1.setEmail("hanmomhanda@naver.com");
        memberDto1.setUserName("hanmomhanda");
        memberDto1.setStatus(Status.ACTIVE);
        memberService.save(memberDto1);
    }

    @Test
    public void t02_회원조회결과있음() throws Exception {
        MemberDto memberDto = memberService.findByEmail("hanmomhanda@naver.com");

        assertThat(memberDto).isNotNull();
        assertThat(memberDto.getEmail()).isEqualTo("hanmomhanda@naver.com");
    }


    @Transactional  // 여기에 @Transactional이 있으면 롤백 되어 t04 테스트 성공, 없으면 롤백되지 않으므로 t04 실패
    @Test(expected = IllegalArgumentException.class)
    public void t03_회원2명저장후_예외발생() throws Exception {
        MemberDto memberDto1 = new MemberDto();
        memberDto1.setEmail("hanmomhanda@gmail.com");
        memberDto1.setUserName("hanmomhanda1");
        memberDto1.setStatus(Status.ACTIVE);

        MemberDto memberDto2 = new MemberDto();
        memberDto2.setEmail("homo.efficio@gmail.com");
        memberDto2.setUserName("hanmomhanda2");
        memberDto2.setStatus(Status.ACTIVE);

        MemberDto persistedMemberDto1 = memberService.save(memberDto1);
        MemberDto persistedMemberDto2 = memberService.save(memberDto2);

        if ("hanmomhanda2".equals(persistedMemberDto2.getUserName())) {
            throw new IllegalArgumentException();
        }

        assertThat(persistedMemberDto1.getEmail()).isEqualTo("hanmomhanda@gmail.com");
        assertThat(persistedMemberDto2.getEmail()).isEqualTo("homo.efficio@gmail.com");
    }

    @Test
    public void t04_예외발생후_회원조회시_최초저장1인만조회() throws Exception {
        List<MemberDto> all = memberService.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}
