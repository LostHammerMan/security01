package com.demo.security01.task;

import com.demo.security01.repository.user.UserRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AttachFileCheckTask {

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @Scheduled(cron = "0 * * * * *")  // 매분 0초마다 한번 씩 실행
    public void checkFiles() throws Exception{
        log.info("======== File Check Task Run ==========");
        log.info("==================================");
    }
}
