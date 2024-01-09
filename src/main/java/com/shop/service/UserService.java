package com.shop.service;

import com.shop.domain.entity.user.User;
import com.shop.domain.enums.Role;
import com.shop.dto.UserDto;
import com.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // 회원가입
    public User saveUser(@Valid UserDto userDto) {
        validateDuplicateMember(userDto.getEmail());
        PasswordEncoder passwordEncoder = null;
        User user =  User.builder()
                .email(userDto.getEmail())
                .password( passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    // 중복회원 검증
    public void validateDuplicateMember(String email) {
        User findUser = userRepository.findByEmail(email);

        if (findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    // 로그인 요청 검증을 위한 User 객체
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return (UserDetails) User.builder()
                .name(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
