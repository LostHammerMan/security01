package com.demo.security01;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

@Slf4j
@SpringBootTest
public class MessageTest {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Test
    public void executeMessage() throws Exception {
        log.info(messageSourceAccessor.getMessage("shit.enter"));
    }
}
