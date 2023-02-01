package gdsc.netwalk.common.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;

// 인증되지 않은 요청이 AJAX일경우는 403으로 응답 그외엔 파라메터의 url로 전환.
public class AjaxAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public AjaxAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String ajaxHeader = request.getHeader("X-Requested-With");

        // AJAX 요청인지 검사 (헤더 검사, 비동기인지 체크)
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);

        // 403 Error를 반환한다.
        if (isAjax) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "세션 만료로 인해서 거부되었습니다.");
        } else {
            super.commence(request, response, authException);
        }
    }
}