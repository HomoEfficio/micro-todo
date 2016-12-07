package homo.efficio.micro.todo.member.domain;

import homo.efficio.micro.todo.member.etc.code.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by hanmomhanda on 2016-11-13.
 */
@Entity
public class Member extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String userName;

    @NotNull
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
