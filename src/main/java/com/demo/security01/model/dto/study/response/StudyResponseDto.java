package com.demo.security01.model.dto.study.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private Set<String> recruitPositions;
    private String contactMethod;
    private String contactAddress;
    private Set<String> skillTags;
    private Integer isFin;

    @Builder
    public StudyResponseDto(String categoryName, String title, String contents, Integer recruitedNumber, String progressPeriod, LocalDate recruitDeadline, Set<String> recruitPositions, String contactMethod, String contactAddress, Set<String> skillTags, Integer isFin) {
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
