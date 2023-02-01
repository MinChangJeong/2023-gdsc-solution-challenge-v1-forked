package gdsc.netwalk.common.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/*
* WebSecurityConfigurerAdapter deprecated (컴포넌트 기반의 보안설정을 권장 목적)
* 변경된 점: 반환값 o / 빈 등록 o
* SecurityFilterChain 을 반환하고 빈으로 등록함으로써 컴포넌트 기반의 보안 설정 가능
* */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig{

    /*
    *  Spring Security -> HttpSecurity 담당
    *   1. 리소스(URL) 접근 권한 설정
    *   2. 인증 전체 흐름에 필요한 Login, Logout 페이지 인증완료 후 페이지 인증 실패 시 이동페이지 등등 설정
    *   3. 인증 로직을 커스텀 하기 위한 커스텀 필터 설정
    *   4. 기타 csrf, 강제 https 호출 등 모든 Spring Security 설정
    * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Http Only Cookie는 클라이언트측 스크립트가 데이터에 접근하는 것을 방지하는 브라우저 쿠크에 추가된 태그
        http
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            // .csrf().disable().authorizeRequests()
        
        // 요청에 대한 인가 설정
        http
            .authorizeHttpRequests()
            // 특정 리소스에 대한 권한 설정
            .requestMatchers("/api/v1/**").permitAll()
            // 추후에 멘티/멘토에 따라서 ROLE을 지정할 필요가 있음.
            // .requestMatchers("/api/v1/menti/**").hasAnyRole("MENTI")
            // .requestMatchers("/api/v1/mento/**").hasAnyRole("MENTO")
            // 접근 허용 리소스 및 인증후 특정 레벨의 권한을 가진 사용자만 접근 가능한 리소스를 설정
            // 그외 나머지 리소스들은 무조건 인증을 완료해야 접근 가능함.
            .anyRequest().authenticated();

        // 인증되지 않은 요청중 AJAX 요청일 경우 403으로 응답, AJAX 요청이 아닐경우 Redirect
        http
            .exceptionHandling()
            .authenticationEntryPoint(new AjaxAuthenticationEntryPoint("[redirect URL]"));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // PathRequest.toStaticResources() : spring boot 가 제공하는 static 리소스들의 기본위치를 가져와 제외
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}