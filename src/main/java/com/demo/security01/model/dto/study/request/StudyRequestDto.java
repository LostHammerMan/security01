package com.demo.security01.model.dto.study.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter @Setter
@ToString
@NoArgsConstructor
public class StudyRequestDto {

    private Long cateCode;
    private String title;
    private String contents;
    private Integer recruitedNumber;
    private String progressPeriod;
    private String progressMethod; // 온라인, 오프라인
    private String recruitDeadline;
    private Set<Long> recruitPositions;
    private String contactMethod;
    private String contactAddress;
    private List<Long> skillTagIdx;

    @Builder
    public StudyRequestDto(Long cateCode, String title, String contents, Integer recruitedNumber, String progressPeriod, String progressMethod, String recruitDeadline, Set<Long> recruitPositions, String contactMethod, String contactAddress, List<Long> skillTagIdx) {
        this.cateCode = cateCode;
        this.title = title;
        this.contents = contents;
        this.recruitedNumber = recruitedNumber;
        this.progressPeriod = progressPeriod;
        this.progressMethod = progressMethod;
        this.recruitDeadline = recruitDeadline;
        this.recruitPositions = recruitPositions;
        this.contactMethod = contactMethod;
        this.contactAddress = contactAddress;
        this.skillTagIdx = skillTagIdx;
    }
}
