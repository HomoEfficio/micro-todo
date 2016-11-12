package homo.efficio.toy.member.dto;

import homo.efficio.toy.member.etc.code.Status;

/**
 * Created by hanmomhanda on 2016-11-13.
 */

public class MemberDto {

    private Long id;

    private String userName;

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
