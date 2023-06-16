package com.demo.security01.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class UserAddr {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDR_IDX")
    private Long addrIdx;

    private String zipcode;

    @Column(name = "POST_ADDR1")
    private String postAddr1;

    @Column(name = "POST_ADDR2")
    private String postAddr2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
}

