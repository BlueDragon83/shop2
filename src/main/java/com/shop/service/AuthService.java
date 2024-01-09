package com.shop.service;

import com.shop.domain.entity.user.User;
import com.shop.dto.auth.SignupDto;
import com.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Objects;


@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CartService cartService;
    private final OrderService orderService;

    @Transactional
    public Long signup(SignupDto signupDto) {
        /* 비밀번호 암호화 */
        signupDto.encryptPassword(bCryptPasswordEncoder.encode(signupDto.getPassword()));
        //Dto to Entity
        User entity = signupDto.toEntity();
        logger.info("To Entity : " + entity.toString());

        //Save
        User user = userRepository.save(entity);
        logger.info("To Entity : " + user.toString());
        return user.getId();
    }
}
