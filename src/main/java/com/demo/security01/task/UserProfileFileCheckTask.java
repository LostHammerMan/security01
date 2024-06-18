package com.demo.security01.task;

import com.demo.security01.entity.user.UserProfile;
import com.demo.security01.repository.user.UserRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@EnableScheduling
public class UserProfileFileCheckTask {

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @Value("${file.dir}")
    private  String fileDir;

    // 일정 시간에 작동할 작업
//    @Scheduled(cron = "0 10 13 * * *")
//    @Scheduled(cron = "0/15 * * * * *")
    public void checkOlderProfileFile(){
        log.info("=========== UserProfileFileCheckTask ============");
        // 프로필 파일 저장 위치
        String profileFolder = getFolderYesterday(fileDir);

        // DB 에 저장되어 있는 프로필 파일 목록
        List<UserProfile> userProfileList = userRepositoryCustom.getOldProfileFile();

        // DB 에 저장되어 있는 프로필 파일 경로를 List 로 변환
        List<Path> profileFileListPaths = userProfileList.stream()
                        .map(userProfile -> Paths.get(getFolderYesterday(fileDir)
                        + File.separator + userProfile.getFileName()))
                                .collect(Collectors.toList());


        // ProfileFileName 이 존재 -> 그 파일의 경로 반환 후 profileFileListPaths 에 추가
        userProfileList.stream().filter(userProfile ->userProfile.getFileName() != null)
                .map(userProfile -> Paths.get(getFolderYesterday(fileDir)
                + File.separator + userProfile.getFileName()))
                .forEach(p -> profileFileListPaths.add(p));

        log.info("================================================");
        profileFileListPaths.forEach(p -> log.info(String.valueOf(p)));

        // 파일 저장 경로를 File 로 변환
        File targetDir = Paths.get(getFolderYesterday(fileDir)).toFile();
        log.info("targetDir = {}", targetDir);

        // .listFiles() : 폴더 path 에 있는 파일이나 폴더 이름 출력
        // file.toPath() : file 객체를 Path 객체로 변환
        File[] removeProfileFiles = targetDir.listFiles(
                file -> profileFileListPaths.contains(file.toPath()) == false
        );

        for (File file : removeProfileFiles){
            log.info("removeFiles = {}", file);
            log.info("removeFilePath = {}", file.getAbsolutePath());
            file.delete();
        }


    }


    // 어제 날짜 폴더 가져오기
    private String getFolderYesterday(String absolutePath){
        log.info("============= getFolderYesterday ==============");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0); // Calendar.Date = day of month
        String str = sdf.format(cal.getTime());
        log.info("\t 현재시간 = {}", str);
        return absolutePath + File.separator + str.replace("-", File.separator);

    }
}
