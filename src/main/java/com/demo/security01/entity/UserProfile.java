package com.demo.security01.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "user")
@Entity
public class UserProfile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_IDX")
    private int profileIdx;

    @Column(name = "FILE_NAME", unique = true)
    private String fileName;

    @Column(name = "FILE_URL")
    private String fileUrl;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime localDateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
}


