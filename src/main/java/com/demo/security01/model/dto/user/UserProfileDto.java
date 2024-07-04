package com.demo.security01.model.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserProfileDto {

//    <img class="comment_inputProfile" src="${root}api/profileImages/${findLounge.user.userProfile.fileName}"  alt="profile"/>
    private String fileName;

}
