package com.demo.security01.model.dto.study.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class StudyResponseDto {

    private String categoryName;
    private String title;
    private String contents;
    private Integer recruitedNumber;
    private String progressPeriod;
    private LocalDate recruitDeadline;
    private List<String> recruitPositions;
//    private Set<String> recruitPositions;
    private String contactMethod;
    private String contactAddress;
    private List<String> skillTags;
//    private Set<String> skillTags;
    private LocalDateTime regDate;
    private Integer isFin;
    private String username;
    private Integer likeCount;
    private Integer viewCount;
    private String skillImgName;

    @Builder
    public StudyResponseDto(String categoryName, String title, String contents, Integer recruitedNumber, String progressPeriod, LocalDate recruitDeadline, List<String> recruitPositions, String contactMethod, 
    		String contactAddress, List<String> skillTags, Integer isFin, LocalDateTime regDate, String username, Integer viewCount, Integer likeCount, String skillImgName) {
        this.categoryName = categoryName;
        this.title = title;
        this.contents = contents;
        this.recruitedNumber = recruitedNumber;
        this.progressPeriod = progressPeriod;
        this.recruitDeadline = recruitDeadline;
        this.recruitPositions = recruitPositions;
        this.contactMethod = contactMethod;
        this.contactAddress = contactAddress;
        this.skillTags = skillTags;
        this.isFin = isFin;
        this.regDate = regDate;
        this.username = username;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.skillImgName = skillImgName;
    }

    @Override
    public String toString() {
        return "StudyResponseDto{" +
                "categoryName='" + categoryName + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", recruitedNumber=" + recruitedNumber +
                ", progressPeriod='" + progressPeriod + '\'' +
                ", recruitDeadline=" + recruitDeadline +
                ", recruitPositions=" + recruitPositions +
                ", contactMethod='" + contactMethod + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", skillTags=" + skillTags +
                '}';
    }
}
