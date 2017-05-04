package homo.efficio.micro.todo.member.repository;

import homo.efficio.micro.todo.member.domain.Member;
import homo.efficio.micro.todo.member.dto.MemberDto;
import homo.efficio.micro.todo.member.etc.code.Status;
import homo.efficio.micro.todo.member.exception.MemberNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-13.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    private Member member;
    private String userName;
    private String email;

    @Before
    public void setup() throws Exception {
        userName = "homo.efficio";
        email = "homo.efficio@gmail.com";
        member = new Member(userName, email);
    }

    @Test
    public void 회원등록() throws Exception {

        Member persistedMember = repository.save(member);

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test
    public void 회원조회_이메일() throws Exception {

        repository.save(member);

        Optional<Member> optPersistedMember = repository.findByEmail(member.getEmail());

        Member persistedMember = optPersistedMember.orElseThrow(MemberNotFoundException::new);

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test(expected = MemberNotFoundException.class)
    public void 회원조회_이메일_NotFound() throws Exception {

        repository.save(member);

        Optional<Member> optPersistedMember = repository.findByEmail("WRONG_EMAIL");

        Member persistedMember = optPersistedMember.orElseThrow(MemberNotFoundException::new);

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test
    public void 회원조회_userName() throws Exception {

        repository.save(member);

        Optional<Member> optPersistedMember = repository.findByUserName(member.getUserName());

        Member persistedMember = optPersistedMember.orElseThrow(MemberNotFoundException::new);

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test(expected = MemberNotFoundException.class)
    public void 회원조회_userName_NotFound() throws Exception {

        repository.save(member);

        Optional<Member> optPersistedMember = repository.findByUserName("WRONG_USERNAME");

        Member persistedMember = optPersistedMember.orElseThrow(MemberNotFoundException::new);

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test
    public void 회원목록조회_by_Status() throws Exception {

        repository.save(member);
        repository.save(new Member(
                "hanmomhanda",
                "hanmomhanda@gmail.com",
                Status.INACTIVE
        ));
        repository.save(new Member(
                "omwomw",
                "omwomw@gmail.com",
                Status.INACTIVE
        ));

        assertThat(repository.findByStatus(Status.ACTIVE).size()).isEqualTo(1);
        assertThat(repository.findByStatus(Status.INACTIVE).size()).isEqualTo(2);
        assertThat(repository.findByStatus(Status.WITHDRAWN).size()).isEqualTo(0);
    }

    @Test
    public void 회원삭제() throws Exception {

        repository.save(member);
        repository.delete(member);

        assertThat(repository.findAll().size()).isEqualTo(0);
    }

//    @Test(expected = MemberNotFoundException.class)
//    public void 존재하지_않는_회원삭제() throws Exception {
//
//        Member nonExistentMember = new Member("nonMember", "nonMember@no.member");
//        repository.delete(nonExistentMember);
//
//        // 존재하지 않는 값을 삭제해도 DB에서는 아무런 에러를 반환하지 않으므로
//        // JPA Repository에서도 아무 예외 발생하지 않는다
//    }
}
