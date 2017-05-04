package homo.efficio.micro.todo.member.dto;

import homo.efficio.micro.todo.member.etc.code.Status;

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

    public MemberDto() {
    }

    public MemberDto(String userName, String email, Status status) {
        this.userName = userName;
        this.email = email;
        this.status = status;
    }

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
