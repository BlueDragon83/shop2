package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final UserDetailService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    // http 요청에 대한 페이지 설정 (권한, 로그인 페이지, 로그아웃 메소드 등등)

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .requestMatchers("/login", "/signup", "/user").permitAll()
               .requestMatchers("/","/members/**","/item/**","/images/**").permitAll() // 여기는 모두가 다 접근
               .requestMatchers("/admin/**").hasRole("ADMIN") // admin 만 접근
               .anyRequest().authenticated()
               .and()
               .formLogin((formLogin)->
                       formLogin
                               .loginPage("/members/login") // 로그인 페이지 url
                                .defaultSuccessUrl("/") // 로그인 성공 시 url
                                .usernameParameter("email") // 로그인 시 사용할 파라미터 이름 지정 (username 에 들어갈 변수?)
                                .failureUrl("/members/login/error") // 로그인 실패 시 url
               ).logout((logoutConfig)->
                       logoutConfig
                               .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 페이지 url
                               .logoutSuccessUrl("/") // 로그아웃 성공 시 url
               ).userDetailsService(memberService);
       return http.build();
   }

    @Bean
    public AuthenticationManager authenticationManager(
           AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

    // 비번 암호화
    @Bean // BCryptPasswordEncoder 를 Bean 으로 등록해서 사용
    public PasswordEncoder passwordEncoder(){
        // 해킹 시 회원 정보 노출 방지 (비번같은거 털릴 때 대비)_ BCryptPasswordEncoder 의 해시 함수를 이용해서 비번 암호화
        return new BCryptPasswordEncoder();
    }


}
