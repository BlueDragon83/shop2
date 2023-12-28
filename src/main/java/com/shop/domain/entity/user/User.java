package com.shop.domain.entity.user;

import com.shop.domain.Address;
import com.shop.domain.BaseTimeEntity;
import com.shop.domain.entity.item.Order;
import com.shop.domain.enums.Role;
import com.shop.domain.enums.UserStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@DynamicUpdate
public class User extends BaseTimeEntity {

@Id
@Column(name = "user_id")
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name="user_name", nullable = false)
    private String name;

    @Column(name="user_pw")
    private String password;


    @Column(name="phone")
    private String phone;


    private Address address;


    @Column
    private  String picture;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Column(name = "email_agree_yn")
    private String emailAgreeYn;

    @Column(name = "sms_agree_yn")
    private String smsAgreeYn;

    @Builder
    public User(String email, String name, String password, String phone, Address address, String picture, Role role, UserStatus userStatus, String emailAgreeYn, String smsAgreeYn) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.picture = picture;
        this.role = role;
        this.userStatus = userStatus;
        this.emailAgreeYn = emailAgreeYn;
        this.smsAgreeYn = smsAgreeYn;
    }
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public User update(String name, Address address, String picture, Role role, UserStatus userStatus, String emailAgreeYn, String smsAgreeYn ){
        this.name = name;
        this.address = address;
        this.picture = picture;
        this.role = role;
        this.userStatus = userStatus;
        this.emailAgreeYn = emailAgreeYn;
        this.smsAgreeYn = smsAgreeYn;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
