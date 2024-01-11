package com.shop.domain.entity.member;

import com.shop.domain.BaseEntity;
import com.shop.domain.enums.Role;
import com.shop.domain.enums.UserStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Member extends BaseEntity {

@Id
@Column(name = "User_id")
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String password;


    @Column(name="phone")
    private String phone;

    @Column(name="address")
    private String address;


    @Column(name="picture")
    private  String picture;


    @Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Column(name = "email_agree_yn")
    private String emailAgreeYn;

    @Column(name = "sms_agree_yn")
    private String smsAgreeYn;

    public String getRoleKey(){
        return this.role.getKey();
    }
    @Builder
    public Member(String email, String password, String name, String phone, String address, String picture, Role role, UserStatus userStatus, String emailAgreeYn, String smsAgreeYn) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.picture = picture;
        this.role = role;
        this.userStatus = userStatus;
        this.emailAgreeYn = emailAgreeYn;
        this.smsAgreeYn = smsAgreeYn;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .address(address)
                .phone(phone)
                .role(role)
                .build();
    }

   /*
   @OneToMany(mappedBy = "user")
    private List<Order> userOrder = new ArrayList<>();

    // 구매자의 주문상품들
    @OneToMany(mappedBy = "user")
    private List<OrderItem> userOrderItem = new ArrayList<>();

    // 판매자의 판매상품들
    @OneToMany(mappedBy = "seller")
    private List<SaleItem> sellerSaleItem = new ArrayList<>();

    // 판매자의 판매
    @OneToMany(mappedBy = "seller")
    private List<Sale> sellerSale;
    */
}
