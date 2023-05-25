package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest() {

        // 서버 -> 세션 생성 테스트
        // http를 직접 사용할 수 X, 가짜 테스트용
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        // 요청에 응답 쿠키 저장 (클라이언트에서 해당 쿠키를 request에 넣어서 서버에 다시 전달해야 쿠키 유지)
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());  // mySessionId = dkfsodfo~~

        // 서버 -> 세션 조회 테스트
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        // 세션 만료 테스트
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}
