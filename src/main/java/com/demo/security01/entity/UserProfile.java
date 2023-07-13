package com.demo.security01.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserProfile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_IDX")
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "FILE_URL")
    private String fileUrl;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;
}


