package com.demo.security01.config.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class StudyBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job updateIsFinJob;

    //  cron = " 초 분 시 일 월 요일 연도 "
    @Scheduled(cron = "0 0/2 * * * ?") // 5분마다 실행
    public void runJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(updateIsFinJob, jobParameters);
    }
}
