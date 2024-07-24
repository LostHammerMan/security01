package com.demo.security01.config.springbatch;

import com.demo.security01.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@Slf4j
public class StudyBatchConfig {

    private final StudyService studyService;
    private final JobRepository jobRepository;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job updateIsFinJob(){
        log.info("======== StudyBatchConfig - updateIsFinJob =========");
        return jobBuilderFactory.get("updateIsFinJob")
                .incrementer(new RunIdIncrementer()) // 매 실행마다 고유한 Job 인터페이스를 생성하기 위해 사용됨 , RunIdIncrementer : Job이 실행될 때마다 runId 증가 -> 동일한 job을 여러번 실행 하더라도, 매번 새로운 인스턴스로 인식되어 중복 실행 가능
                .start(updateIsFinStep())
                .build();
    }

    // step 구현방식 - Tasklet(배치작업이 쉬운경우), Chunk(대용양 처리시 효과적, 구현 대상이 많아 단순 작업에는 효율적이지 못함)
    // 아래는 Tasklet 방식
    @Bean
    public Step updateIsFinStep(){
        log.info("======== StudyBatchConfig - updateIsFinStep =========");
        return stepBuilderFactory.get("updateIsFinStep")
                .tasklet((contribution, chunkContext) -> {  // tasklet : 단일 작업을 수행한 인터페이스
                    studyService.updateIsFin();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }



}
