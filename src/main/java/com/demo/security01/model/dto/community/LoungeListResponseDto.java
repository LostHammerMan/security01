package com.demo.security01.model.dto.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@EqualsAndHashCode
public class LoungeListResponseDto {

    private Long idx;
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime regDate;
    private String username;
    private String categoryName;
    private String profileFilename;
    private int viewCount;
    private int commentCount;
    private int likeCount;

    @Builder
    public LoungeListResponseDto(Long idx, String title, LocalDateTime regDate, String username, String categoryName, String profileFilename, int viewCount, int commentCount, int likeCount) {
        this.idx = idx;
        this.title = title;
        this.regDate = regDate;
        this.username = username;
        this.categoryName = categoryName;
        this.profileFilename = profileFilename;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }
}
