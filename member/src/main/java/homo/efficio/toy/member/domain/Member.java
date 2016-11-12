package homo.efficio.toy.member.domain;

import homo.efficio.toy.member.etc.code.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by hanmomhanda on 2016-11-13.
 */
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String userName;

    @NotNull
    private String email;

    private Status status;

    public Member(String userName, String email, Status status) {
        this.userName = userName;
        this.email = email;
        this.status = status;
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
