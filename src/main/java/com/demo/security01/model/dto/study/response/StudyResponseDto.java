package com.demo.security01.model.dto.study.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class StudyResponseDto {
	private Long studyIdx;
    private String categoryName;
    private String title;
    private String contents;
    private Integer recruitedNumber;
    private String progressPeriod;
    private String process;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate recruitDeadline;
    private String formattedRecruitDeadline;
    private List<String> recruitPositions;
//    private Set<String> recruitPositions;
    private String contactMethod;
    private String contactAddress;
    private List<String> skillTags;
//    private Set<String> skillTags;
    
    
//    private LocalDateTime regDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime regDate;
    private String formattedRegDate;
    
    
    private Integer isFin;
    private String username;
    private Integer likeCount;
    private Integer viewCount;
    private String skillImgName;

    @Builder
    public StudyResponseDto(Long studyIdx, String categoryName, String title, String contents, Integer recruitedNumber, String progressPeriod, LocalDate recruitDeadline, List<String> recruitPositions, String contactMethod, 
    		String contactAddress, List<String> skillTags, Integer isFin, LocalDateTime regDate, String username, Integer viewCount, Integer likeCount, String skillImgName, String process) {
    	this.studyIdx = studyIdx;
        this.categoryName = categoryName;
        this.title = title;
        this.contents = contents;
        this.recruitedNumber = recruitedNumber;
        this.progressPeriod = progressPeriod;
        this.process = process;
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
        this.formattedRegDate = toformattedDate(this.regDate);
        this.formattedRecruitDeadline = toformattedDate(this.recruitDeadline.atStartOfDay());
    }
    
    
//    private String toformattedRegDate() {
//    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//    	return this.regDate.format(fmt);
//    }
    
    private String toformattedDate(LocalDateTime date) {
    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    	return date.format(fmt);
    }


	@Override
	public String toString() {
		return "StudyResponseDto [studyIdx=" + studyIdx + ", categoryName=" + categoryName + ", title=" + title
				+ ", contents=" + contents + ", recruitedNumber=" + recruitedNumber + ", progressPeriod="
				+ progressPeriod + ", process=" + process + ", recruitDeadline=" + recruitDeadline
				+ ", recruitPositions=" + recruitPositions + ", contactMethod=" + contactMethod + ", contactAddress="
				+ contactAddress + ", skillTags=" + skillTags + ", regDate=" + regDate + ", formattedRegDate="
				+ formattedRegDate + ", isFin=" + isFin + ", username=" + username + ", likeCount=" + likeCount
				+ ", viewCount=" + viewCount + ", skillImgName=" + skillImgName + "]";
	}

	

    
}
