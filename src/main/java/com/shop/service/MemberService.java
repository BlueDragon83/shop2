package com.shop.service;

import com.shop.domain.entity.member.Member;
import com.shop.dto.MemberDto;
import com.shop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // 회원가입
    public Member saveMember(@Valid MemberDto memberDto) {
        String encodePassword = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(encodePassword);
        Member entity = memberDto.toEntity();
        logger.info("To Entity : " + entity.toString());
        //Save
        Member member = memberRepository.save(entity);
        logger.info("To Entity : " + member.toString());
        return member;
    }

    // 중복회원 검증
    public void validateDuplicateMember(String email) {
        Member findMember = memberRepository.findByEmail(email);

        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
