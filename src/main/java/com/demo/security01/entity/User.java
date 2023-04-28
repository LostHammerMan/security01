package com.demo.security01.entity;

import com.demo.security01.model.Role;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

//    private String role;
    @Enumerated(EnumType.STRING) // ENUM 타입의 값이 글자 그대로 저장
    private Role role;

//    private Timestamp loginDate;

    @CreationTimestamp
    private Timestamp createDate;
}
