package com.demo.security01.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${naver.id}")
    private String user_id;

    @Value("${naver.pwd}")
    private String user_pwd;


    @Bean
    public JavaMailSender javaMailService(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.naver.com"); // smtp 서버 주소
        javaMailSender.setUsername(user_id);
        javaMailSender.setPassword(user_pwd);

        javaMailSender.setPort(465);

        javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보 가져오기

        return javaMailSender;
    }

    private Properties getMailProperties(){
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp strattles 사용
        properties.setProperty("mail.debug", "true"); // 디버그 사용
        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com"); // ssl 인증 서버는 smtp.naver.com
        properties.setProperty("mail.smtp.ssl.enable","true"); // ssl 사용

        return properties;
    }



}
