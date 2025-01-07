package com.demo.security01.entity.user;

import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.tag.User_Skilltag;
import com.demo.security01.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

//    private String role;
    @Enumerated(EnumType.STRING) // ENUM 타입의 값이 글자 그대로 저장
    private Role role;

    @Column(name = "NICK_NAME")
    private String nickName;

    /*@OneToOne
    @JoinColumn(name = "THUMBNAIL_ID")
    private UserProfile userThumbnail;*/
    
    @OneToMany(mappedBy = "user")
    private List<BoardLike> boardLikes;

    @OneToOne(mappedBy = "user")
    @Nullable
    private UserProfile userProfile;

    @OneToOne(mappedBy = "user")
    @Nullable
    private UserAddr userAddr;


    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private LoungeEntity loungeEntity;*/

//    private Timestamp loginDate;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp createDate;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User_Skilltag> user_skillTag;


    /*public void setCreateDate(LocalDateTime createDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        this.createDate = );
    }*/
}
