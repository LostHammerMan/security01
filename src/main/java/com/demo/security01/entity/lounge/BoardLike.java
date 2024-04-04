package com.demo.security01.entity.lounge;

import com.demo.security01.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class BoardLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_IDX")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOUNGE_IDX")
    private LoungeEntity lounge;


}
