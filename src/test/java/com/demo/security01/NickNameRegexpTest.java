package com.demo.security01;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

@SpringBootTest
@Slf4j
public class NickNameRegexpTest {

    // (?!.*\s) : 공백을 허용하지 않음
    private static final String regexp = "^[가-힣a-zA-Z0-9]{1,10}";
    String input = "망치맨 A1";
    String result = input.replaceAll("\\s", "");

    @Test
    public void regexpMatch(){
        log.info("================================================");
        if (!Pattern.matches(regexp, result)){
            log.info("\t 유효하지 않은 형식입니다");
        }else {
            log.info("\t 유효한 형식입니다");
        }
        log.info("result = {}", result);
    }

}
