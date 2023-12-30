package com.shop.domain.entity.member;

import com.shop.domain.BaseTimeEntity;
import com.shop.domain.entity.item.Order;
import com.shop.domain.enums.Role;
import com.shop.domain.enums.MemberStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Member extends BaseTimeEntity {

@Id
@Column(name = "member_id")
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="name", nullable = false)
    private String name;

    private String password;


    @Column(name="phone")
    private String phone;


    private String address;


    @Column
    private  String picture;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_status", nullable = false)
    private MemberStatus memberStatus;

    @Column(name = "email_agree_yn")
    private String emailAgreeYn;

    @Column(name = "sms_agree_yn")
    private String smsAgreeYn;

    public String getRoleKey(){
        return this.role.getKey();
    }
    @Builder
    public Member(String email, String name, String password, String phone, String address, String picture, Role role, MemberStatus memberStatus, String emailAgreeYn, String smsAgreeYn) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.picture = picture;
        this.role = role;
        this.memberStatus = memberStatus;
        this.emailAgreeYn = emailAgreeYn;
        this.smsAgreeYn = smsAgreeYn;
    }
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
