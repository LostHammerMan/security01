package com.demo.security01.model.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class TickParser_ProfileImg {

    private static final Tika tika = new Tika();

    public static boolean validImgFile(InputStream inputStream){
        try {
            List<String> notValidTypeList =
                    Arrays.asList("image/jpeg", "image/pjpeg", "image/png", "image/gif",
                            "image/bmp", "image/x-windows-bmp");

            String mimeType = tika.detect(inputStream);
            log.info("MimeType = {}", mimeType);

            boolean isValid = notValidTypeList.stream()
                    .anyMatch(notValidType -> notValidType.equalsIgnoreCase(mimeType));

            return isValid;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
