package homo.efficio.toy.member.repository;

import homo.efficio.toy.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by hanmomhanda on 2016-11-13.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserName(String userName);

    Optional<Member> findByEmail(String email);
}
