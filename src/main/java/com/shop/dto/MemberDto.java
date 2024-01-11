package com.shop.dto;

import com.shop.domain.entity.member.Member;
import com.shop.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter @Setter
public class MemberDto {

     @NotBlank(message = "이름은 필수 입력 값입니다.")
     private String name;

     @NotBlank(message = "이메일은 필수 입력 값입니다.")
     @Email(message = "이메일 형식으로 입력해주세요.")
     private String email;

     @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
     @Length(min = 8, max = 16, message = "비밀번호는8자 이상, 16자 이하로 입력해주세요")
     private String password;

     @NotBlank(message = "주소는 필수 입력 값입니다.")
     private String address;

     private String phone;
     private String role;

     /* 암호화된 password */
     public void encryptPassword(String BCryptpassword) {
          this.password = BCryptpassword;
     }

     public Member toEntity() {
          return Member.builder()
                  .email(email)
                  .password(password)
                  .name(name)
                  .address(address)
                  .phone(phone)
                  .role(Role.USER)
                  .build();
     }

}