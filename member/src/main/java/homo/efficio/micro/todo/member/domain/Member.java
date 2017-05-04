package homo.efficio.micro.todo.member.domain;

import homo.efficio.micro.todo.member.etc.code.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-13.
 */
@Entity
public class Member extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)  // 새 테이블 생성할 때만 효과, 기존 테이블에는 효과 없음
    private String userName;

    @NotNull
    @Column(unique = true)  // 새 테이블 생성할 때만 효과, 기존 테이블에는 효과 없음
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Member() {
        this.status = Status.ACTIVE;
    }

    public Member(String userName, String email) {
        this.userName = userName;
        this.email = email;
        this.status = Status.ACTIVE;
    }

    public Member(String userName, String email, Status status) {
        this.userName = userName;
        this.email = email;
        this.status = status;
    }

    public Member(Long id, String userName, String email, Status status) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
