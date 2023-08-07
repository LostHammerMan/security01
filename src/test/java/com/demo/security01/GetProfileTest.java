package com.demo.security01;

import com.demo.security01.entity.UserProfile;
import com.demo.security01.repository.user.UserRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class GetProfileTest {

    @Mock
    private UserRepositoryCustom userRepositoryCustom;

    @Value("${file.dir}")
    private String fileDir;


    @Test
    public void getOlderProfile(){

        String folder = getFolderYesterday(fileDir);
        log.info("folder = {}", folder);

        List<UserProfile> userProfile1 = userRepositoryCustom.getOldProfileFile();

        // DB에 저장되어 있는 모든 파일 경로
        List<Path> fileListPaths = userProfile1.stream()
                .map(profile -> Paths.get(getFolderYesterday(fileDir) + "\\" + profile.getFileName()))
                .collect(Collectors.toList());


        //
        userProfile1.stream().filter(profile -> profile.getFileName() != null) // f
                        .map(profile -> Paths.get(getFolderYesterday(fileDir)
                                + File.separator + profile.getFileName()))
                        .forEach(p -> fileListPaths.add(p));
        log.info("=====================================================");
        fileListPaths.forEach(p -> log.info(String.valueOf(p)));

        log.info("\t fileListPaths = {}", fileListPaths);

        File targetDir = Paths.get(getFolderYesterday(fileDir)).toFile();
        log.info("targetDir = {}", targetDir);

        File[] removeFiles = targetDir.listFiles(
                file -> fileListPaths.contains(file.toPath())== false);

        for (File file : removeFiles){
            log.info("removeFile = {}", file);
            log.info("removeFilePath = {}", file.getAbsolutePath());
            file.delete();
        }
    }

    // 어제 날짜 폴더
    private String getFolderYesterday(String absolutePath){
        log.info(" ======= getFolderYesterday ========");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String str = sdf.format(cal.getTime());
        log.info("\t\t str = {}", str);

        // File.separator :  프로그램이 실행 중인 OS에 해당하는 구분자를 리턴합니다
//        return  absolutePath + "\\" + str.replace("-", File.separator);
        return  absolutePath + File.separator + str.replace("-", File.separator);
    }
}
