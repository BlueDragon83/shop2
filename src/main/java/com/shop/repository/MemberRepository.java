package com.shop.repository;

import com.shop.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메소드입니다.
    Optional<Member> findByOne(Long id);

    Member findByEmail(String email);
    Optional<Member> findByName(String userName);
    Boolean existsUserByUserId(Long id);

    Optional<Member>  findByUserStatus(Long id);


}