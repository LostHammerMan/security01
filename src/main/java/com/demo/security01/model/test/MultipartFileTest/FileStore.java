package com.demo.security01.model.test.MultipartFileTest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore { // 파일 저장과 관련된 업무 처리

    @Value("${file.dir}")
    private String fileDir;

    // 전체 경로
    private String getFullPath(String fileName){

        File file = new File(fileDir);

        if (!file.exists()){
            file.mkdirs();
        }

        return fileDir + fileName;
    }

    // 단일 파일
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()){
            return null;
        }
        String originalFileName = multipartFile.getOriginalFilename(); // 본래 파일명
        String storeFileName = createStoreFileName(originalFileName); // uuid 파일명

        // 저장
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFileName, storeFileName);
    }


    // 다중 파일
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        List<UploadFile> storeFileResult = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles){
            if (!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    // 서버 내부에서 관리하는 파일명 uuid 를 통해 충돌 안나게 처리
    private String createStoreFileName(String originalFileName){
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    // 확장자 별도로 추출 -> 서버에서 관리하는 파일명에도 붙여줌
    private String extractExt(String originalFileName){

        // 탐색하는 문자열이 마지막으로 등장하는 위치에 대한 index 반환
         int pos = originalFileName.lastIndexOf(".");
         return originalFileName.substring(pos + 1);
    }

}
