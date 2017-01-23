package homo.efficio.micro.todo.member.repository;

import homo.efficio.micro.todo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-11-13.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserName(String userName);

    Optional<Member> findByEmail(String email);

    List<Member> findAll();
}
