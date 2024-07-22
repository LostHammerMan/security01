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
                .incrementer(new RunIdIncrementer())
                .start(updateIsFinStep())
                .build();
    }

    @Bean
    public Step updateIsFinStep(){
        log.info("======== StudyBatchConfig - updateIsFinStep =========");
        return stepBuilderFactory.get("updateIsFinStep")
                .tasklet((contribution, chunkContext) -> {
                    studyService.updateIsFin();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }



}
