package com.shop.domain.user;

import com.shop.domain.Address;
import com.shop.domain.BaseEntity;
import com.shop.domain.item.Order;
import lombok.*;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(unique = true)
    private String email;
    
    private String password;

    @Embedded
    private Address address;

    @Column
    private String picture;

    //@Enumerated(EnumType.STRING) - JPA로 데이터베이스에 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정합니다.
    //기본적으로는 int로 된 숫자가 저장됩니다.
    //숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없습니다.
    //그래서 문자열로 저장될 수 있도록 선언합니다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String password, Address address, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, Address address, String picture, Role role){
        this.name = name;
        this.address = address;
        this.picture = picture;
        this.role = role;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
