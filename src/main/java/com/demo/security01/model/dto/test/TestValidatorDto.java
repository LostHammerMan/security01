package com.demo.security01.model.dto.test;

import com.demo.security01.config.annotation.TestAddrAnno;
import com.demo.security01.config.annotation.TestEmailAddrAnno;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class TestValidatorDto {

    @TestEmailAddrAnno
    String testEmailAddr;

    @TestAddrAnno
    String addr;

    String userName;
}
