package com.demo.security01.task;

import com.demo.security01.repository.user.UserRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
//@EnableScheduling // 이 어노테이션 없으면 실행 안됨 -> 다른 클래스에 @EnableScheduling 있으면 실행된다
@Slf4j
public class AttachFileCheckTaskTest {

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

   /* @Scheduled(cron = "0 * * * * *")  // 매분 0초마다 한번 씩 실행
    public void checkFiles() throws Exception{
        log.info("======== File Check Task Run ==========");
        log.info("==================================");
    }*/
}
