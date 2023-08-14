package com.demo.security01.entity.lounge;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "LOUNGE_ENTITY")
public class LoungeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOUNGE_IDX")
    private Long idx;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "COUNT")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_IDX")
    private User user;

    @OneToOne
    @JoinColumn(name = "CATE_CODE")
    private CategoryEntity cateCode;

    @CreationTimestamp
    @Column(name = "REG_DATE")
    private Timestamp regDate;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;


}
