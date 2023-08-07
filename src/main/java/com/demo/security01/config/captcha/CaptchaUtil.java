package com.demo.security01.config.captcha;


import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CaptchaUtil {


    // Captcha Image 생성
    public void captchaImg(HttpServletRequest request, HttpServletResponse response){

        // 폰트 및 컬러 설정


        Captcha captcha = new Captcha.Builder(200, 80)

                // 6자리 숫자 설정
                .addText(new NumbersAnswerProducer(6))
                .addNoise().addNoise().addNoise()
                .addBackground(new GradiatedBackgroundProducer())
                .addBorder()
                .build();

        // JSP 에서 captcha 객체에 접근할 수 있도록 세션에 저장
        request.getSession().setAttribute(Captcha.NAME, captcha);
        CaptchaServletUtil.writeImage(response, captcha.getImage());
    }

    //
    public void getAudioCaptCha(HttpServletRequest req, HttpServletResponse res, String answer) throws IOException {
        HttpSession session = req.getSession();

        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
        String getAnswer = answer;

        if(getAnswer == null || getAnswer.equals("")) getAnswer = captcha.getAnswer();

        AudioCaptcha audiocaptcha = new AudioCaptcha.Builder()
                .addAnswer(new SetTextProducer(getAnswer))
                .addNoise()
                .build();

        CaptchaServletUtil.writeAudio(res, audiocaptcha.getChallenge());
    }


// 제출 답안 확인 프로세스
 /*   public String chkAnswerProcess(HttpServletRequest request, HashMap<String, String> p){
        return "confirm";
    }
*/


}
