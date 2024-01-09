package com.shop.controller;


import com.shop.dto.auth.SignupDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.shop.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AuthService authService;

    @GetMapping("/signin")
    public String SigninForm() {
        return "signinForm";
    }

    @GetMapping("/signup")
    public String SignupForm() {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult, Model model) {
        /* 검증 */
        if(bindingResult.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 값 유지 */
            model.addAttribute("signupDto", signupDto);

            /* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
                log.info("회원가입 실패 ! error message : "+error.getDefaultMessage());
            }

            /* Model에 담아 view resolve */
            for(String key : errorMap.keySet()) {
                model.addAttribute(key, errorMap.get(key));
            }

            /* 회원가입 페이지로 리턴 */
            return "auth/signinForm";
        }
        // 회원가입 성공 시
        authService.signup(signupDto);
        log.info("회원가입 성공");
        return "redirect:/auth/login";
    }

    // 로그인 실패
    @GetMapping("/signin/fail")
    public String userLoginFail(Model model) {
        model.addAttribute("signinFailMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "auth/signinForm";
    }
}
