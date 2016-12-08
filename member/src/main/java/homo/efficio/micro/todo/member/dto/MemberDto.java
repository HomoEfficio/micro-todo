package homo.efficio.micro.todo.member.dto;

import homo.efficio.micro.todo.member.etc.code.Status;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-13.
 */

public class MemberDto {

    private Long id;

    @NotNull
    @Size(min = 8)
    private String userName;

    @NotNull
    private String email;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
