package homo.efficio.toy.member.repository;

import homo.efficio.toy.member.domain.Member;
import homo.efficio.toy.member.exception.MemberNotFoundException;
import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hanmomhanda on 2016-11-13.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

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

        Member persistedMember = optPersistedMember.orElseThrow(() -> new MemberNotFoundException());

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test(expected = MemberNotFoundException.class)
    public void 회원조회_이메일_NotFound() throws Exception {

        repository.save(member);

        Optional<Member> optPersistedMember = repository.findByEmail("WRONG_EMAIL");

        Member persistedMember = optPersistedMember.orElseThrow(() -> new MemberNotFoundException());

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test
    public void 회원조회_userName() throws Exception {

        repository.save(member);

        Optional<Member> optPersistedMember = repository.findByUserName(member.getUserName());

        Member persistedMember = optPersistedMember.orElseThrow(() -> new MemberNotFoundException());

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }

    @Test(expected = MemberNotFoundException.class)
    public void 회원조회_userName_NotFound() throws Exception {

        repository.save(member);

        Optional<Member> optPersistedMember = repository.findByUserName("WRONG_USERNAME");

        Member persistedMember = optPersistedMember.orElseThrow(() -> new MemberNotFoundException());

        assertThat(persistedMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(persistedMember.getUserName()).isEqualTo(member.getUserName());
    }
}
