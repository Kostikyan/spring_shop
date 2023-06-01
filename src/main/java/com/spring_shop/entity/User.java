package com.spring_shop.entity;

import com.spring_shop.entity.types.UserType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;

    @Enumerated(value = EnumType.STRING)
    private UserType type;
}
