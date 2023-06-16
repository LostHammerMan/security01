package com.demo.security01.service.user;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Random;

@Service
public class MailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    private String ePw;
    // 메일 내용 작성
    public MimeMessage createMessage(String to) throws Exception{

        /*MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("template/email.mustache");

        StringWriter writer = new StringWriter();
        m.execute(writer, to);
        writer.flush();
        String messageText = writer.toString();*/

        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("안녕하세요 회원가입 이메일 인증");

        String msg = "";
        msg += "<div style='margin:100px'>";
        msg += "<h1> 그냥 빨리 가입해 </h1>";
        msg += "<br>";
        msg += "<p>코드야 빨리 받아 적어</p>";
        msg += "<br>";
        msg += "<h3>인증코드</h3>";
        msg += "Code : <strong>";
        msg += ePw + "</strong>";
        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress("seong7577@naver.com", "LostHammerMan"));

        return message;

    }


    // 랜덤 인증 코드
    public String createAuthKey() {
        // StringBuffer : 문자열 추가, 변경
        StringBuffer key = new StringBuffer();

        // Random : 자바에서 난수 생성
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            // .nextInt() : 무작위 인트값 반환
            // .nextInt(4) : 0 ~ 3 까지 무작위 int 값 리턴
            int index = rnd.nextInt(3); // 0 ~ 2 까지 무작위 값 리턴, rnd 값에 따라 switch 문 실행

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a ~ z 추가(1 + 97 => (char) 98  = 'b'
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A ~ Z 추가
                    break;

                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0 ~ 9 추가
                    break;
            }

        }
        return key.toString();
    }

    // 메일 발송
    // to : 이메일 주소로 사용
    // MimeMessage 객체 안에 전송할 메일의 내용을 담음
    public String sendSimpleMessage(String to) throws Exception{
        ePw = createAuthKey(); // 인증코드 생성

        MimeMessage message = createMessage(to);

        try {
         emailSender.send(message);
        } catch (MailException me){
            me.printStackTrace();
            throw new IllegalArgumentException();
        }

        return ePw; // 메일로 보냈던 인증코드를 서버로 반환

    }

}
