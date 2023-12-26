package com.shop.service;

import com.shop.domain.user.Role;
import com.shop.domain.user.User;
import com.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional // 롤백!
@RequiredArgsConstructor // @AutoWired 없이 new 해주기
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User saveUser(User user){

        return userRepository.save(user);
    }


    private void validateDuplicatedMember(User user) {
        User findUsers = userRepository.findByEmail(user.getEmail());
        if (findUsers != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    // 로그인 로그아웃 구현!
    @Override  // loadUserByUsername 오버라이딩
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new UsernameNotFoundException(email);
        }

        return (UserDetails) User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
