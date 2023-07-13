package com.demo.security01.service.user;

import com.demo.security01.model.test.MultipartFileTest.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

// 파일 저장과 관련된 업무처리
@Slf4j
@Service
public class UserProfileUploadService {

    @Value("${file.dir}")
    private String fileDir;
//    private String fileDir = "C:/test/springboot/upload/";

    // 전체 경로 + 파일
    public String getFullPath(String fileName){
        File file = new File(fileDir);

        if (!file.exists()){
            file.mkdirs();
        }

        return fileDir + fileName;
    }

    // 프로필 이미지 파일 저장
    public UploadFile uploadProfileImg(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()){
            return null;
        }

        // originalFileName -> storeFileName(UUID.확장자)로 변환
        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        // 파일 해당 경로에 저장
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFileName, storeFileName);
    }

    private  String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        String uuidFix = uuid.substring(0,10);
        String fileName = uuidFix + "." + ext;
        return fileName;
    }

    private  String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

    // 업로드 한 파일 삭제
    public void deleteProfileImg(String fileName) throws IOException {
        org.springframework.core.io.Resource resource = new UrlResource("file:" + getFullPath(fileName));
        log.info("resource = {}", resource);
        File file = new File(resource.getFile().toURI());
        file.delete();

    }
}
