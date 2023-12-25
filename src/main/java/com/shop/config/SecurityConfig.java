package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 설정파일을 만들기 위한 애노테이션 or Bean을 등록하기 위한 애노테이션
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    MemberService memberService;

    // http 요청에 대한 페이지 설정 (권한, 로그인 페이지, 로그아웃 메소드 등등)

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .requestMatchers("/login", "/signup", "/user").permitAll()
               .requestMatchers("/","/members/**","/item/**","/images/**").permitAll() // 여기는 모두가 다 접근
               .requestMatchers("/admin/**").hasRole("ADMIN") // admin 만 접근
               .anyRequest().authenticated()
               .and()
               .formLogin()
               .loginPage("/members/login") // 로그인 페이지 url
               .defaultSuccessUrl("/") // 로그인 성공 시 url
               .usernameParameter("email") // 로그인 시 사용할 파라미터 이름 지정 (username 에 들어갈 변수?)
               .failureUrl("/members/login/error") // 로그인 실패 시 url
               .and()
               .logout()
               .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 페이지 url
               .logoutSuccessUrl("/"); // 로그아웃 성공 시 url
       return http.build();
   }

    // http 요청에 대한 보안 설정 (권한, 로그인 페이지, 로그아웃 메소드 등등)
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(memberService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
    @Bean
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }
            // AuthenticationManager 로 인증함
            // AuthenticationManager 는 ~Builder 로 만듦
            // auth 에 이거를 다 담아서 인증을 하는거지
                // memberService 를 저장한다.(로그인 로그아웃 + 중복가입방지 + 중복 아니면 member save)
                // 비번 암호화까지 해버림


    // 비번 암호화
    @Bean // BCryptPasswordEncoder 를 Bean 으로 등록해서 사용
    public PasswordEncoder passwordEncoder(){
        // 해킹 시 회원 정보 노출 방지 (비번같은거 털릴 때 대비)_ BCryptPasswordEncoder 의 해시 함수를 이용해서 비번 암호화
        return new BCryptPasswordEncoder();
    }


}
