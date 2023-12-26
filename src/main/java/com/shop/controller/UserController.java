package com.shop.controller;

import com.shop.dto.UserFormDto;
import com.shop.domain.user.User;
import com.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 회원가입 controller

@Controller
@RequiredArgsConstructor // @Autowired 대신
@RequestMapping("/users")
public class UserController {

    private final UserService userService; // final 로 해야함
    private final PasswordEncoder passwordEncoder;

    // 회원가입 FORM
    @GetMapping("/new")
    public String userForm(Model model){

        UserFormDto userFormDto = new UserFormDto();

        model.addAttribute("userFormDto", userFormDto);
        return "user/userForm";
    }

    // 회원가입 성공 시 메인으로 리다이렉트
    // 실패하면 다시 회원가입 페이지로 돌아감
    @PostMapping("/new")
    public String newUser (@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "user/userForm";
        }
        try{
            //User user = User.createUser(userFormDto, passwordEncoder);
           //userService.saveUser(user);

        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/userForm";
        }
        return "redirect:/";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginUser(){
        return "user/userLoginForm";
    }

    // 로그인 페이지_뭔가 오류났을 때
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비번 확인ㄱ");
        return "user/userLoginForm";
    }

}
