package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;            // 시퀀스로 부여되는 아이디

    @NotEmpty
    private String loginId;     // 로그인 ID
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
