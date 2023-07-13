package com.demo.security01.model.dto.user.modifyUser;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class ModifyUserProfileDto {

    public String profileImgName;
    public String profileImgPath;
    public MultipartFile profileImg;
    public LocalDateTime localDateTime;
    public String nickName;
    private int userIdx;
}