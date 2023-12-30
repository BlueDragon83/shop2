package com.shop.service;

import com.shop.domain.entity.member.Member;
import com.shop.domain.enums.Role;
import com.shop.dto.JoinFormDto;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 회원가입
    public Member saveMember(@Valid JoinFormDto joinFormDto) {
        validateDuplicateMember(joinFormDto.getEmail());
        PasswordEncoder passwordEncoder = null;
        Member member =  Member.builder()
                .name(joinFormDto.getName())
                .email(joinFormDto.getEmail())
                .address(joinFormDto.getAddress())
                .password( passwordEncoder.encode(joinFormDto.getPassword())) // BCryptPasswordEncoder Bean 을 파라미터로 넘겨서 비번을 암호화함
                .role(Role.USER)
                .build();
        return memberRepository.save(member);
    }

    // 중복회원 검증
    public void validateDuplicateMember(String email) {
        Member findMember = memberRepository.findByEmail(email);

        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    // 로그인 요청 검증을 위한 User 객체
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }



}
