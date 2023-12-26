package com.shop.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserFormDto {
     // (validated annotation)
     @NotBlank(message = "이름입력") // null, .length=0, ""
     private  String name;

     @NotEmpty(message = "이메일입력") // null, .length=0
     @Email // 이메일 형식?
     private String email;

     @NotEmpty(message = "비번입력") // null, .length=0
     @Length(min=8, max=16, message = "8~16ㄱ")
     private String password;


     @NotEmpty(message = "주소입력") // null, .length=0
     private String address;

}
