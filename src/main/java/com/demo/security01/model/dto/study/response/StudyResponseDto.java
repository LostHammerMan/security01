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

    private Long cateCode;
    private String title;
    private String contents;
    private Integer recruitedNumber;
    private String progressPeriod;
    private LocalDate recruitDeadline;
    private Set<String> recruitPositions;
    private String contactMethod;
    private String contactAddress;

    @Builder
    public StudyResponseDto(Long cateCode, String title, String contents, Integer recruitedNumber, String progressPeriod, LocalDate recruitDeadline, Set<String> recruitPositions, String contactMethod, String contactAddress) {
        this.cateCode = cateCode;
        this.title = title;
        this.contents = contents;
        this.recruitedNumber = recruitedNumber;
        this.progressPeriod = progressPeriod;
        this.recruitDeadline = recruitDeadline;
        this.recruitPositions = recruitPositions;
        this.contactMethod = contactMethod;
        this.contactAddress = contactAddress;
    }
}
