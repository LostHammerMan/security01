package com.demo.security01.service.user;

import com.demo.security01.config.exception.UserNotMatchException;
import com.demo.security01.entity.tag.SkillTagEntity;
import com.demo.security01.entity.tag.User_Skilltag;
import com.demo.security01.entity.user.User;
import com.demo.security01.entity.user.UserAddr;
import com.demo.security01.entity.user.UserProfile;
import com.demo.security01.model.Role;
import com.demo.security01.model.dto.user.JoinUserDto;
import com.demo.security01.model.dto.user.UserProfileDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserEmailDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserProfileDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserPwdDto;
import com.demo.security01.repository.study.study_skill.SkillTagRepository;
import com.demo.security01.repository.user.UserAddrRepository;
import com.demo.security01.repository.user.UserProfileRepository;
import com.demo.security01.repository.user.UserRepository;
import com.demo.security01.repository.user.UserRepositoryCustom;
import com.demo.security01.repository.user.user_skill.User_skillTagRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepositoryCustom;
    private final UserProfileRepository profileRepository;
    private final UserProfileUploadService profileUploadService;
    private final UserAddrRepository addrRepository;
    private final PasswordEncoder encoder;
    private final ResourceLoader resourceLoader;
    private final SkillTagRepository skillTagRepository;
    private final User_skillTagRepository user_skillTagRepository;
    

    @Value("${file.dir}")
    String filePath;



    // 회원가입
    @Transactional
    public void save(JoinUserDto joinUserDto) {
        log.info("======== UserSave Service ============");

        String rawPwd = joinUserDto.getPassword();
        String encPwd = encoder.encode(rawPwd);

        String email_id = joinUserDto.getEmail_id();
        String email_domain = joinUserDto.getEmail_domain();
        String email_addr = email_id + email_domain;

        joinUserDto.setEmail_addr(email_addr);

        User user = new User();
        user.setUsername(joinUserDto.getUsername());
        user.setEmail(joinUserDto.getEmail_addr());
        user.setPassword(encPwd);
        user.setRole(Role.USER);
        log.info("user = {}", user);

        UserProfile defaultProfile = new UserProfile();

        userRepository.save(user);

        defaultProfile.setUser(user);
        defaultProfile.setFileUrl("C:\\test\\springboot\\upload\\");
        defaultProfile.setFileName("simuruk.png");
        defaultProfile.setLocalDateTime(LocalDateTime.now());

        profileRepository.save(defaultProfile);
        
        if(joinUserDto.getSkillTagIdx() != null) {
        	for(Long skillTagIdx : joinUserDto.getSkillTagIdx()) {
        		SkillTagEntity skillTag = skillTagRepository.findById(skillTagIdx)
        				.orElseThrow(
        						() -> new EntityNotFoundException("해당 스킬은 존재하지 않습니다")
						);
    		
        	User_Skilltag user_Skilltag = User_Skilltag.builder()
        			.user(user)
        			.skillTag(skillTag)
        			.build();
        	
        	user_skillTagRepository.save(user_Skilltag);
        	}
        }
    }

    public Resource getDefaultProfileImg(){
        return resourceLoader.getResource("classpath:static/img/simuruk.png");
    }

    // 아이디 중복 체크
    public boolean existByUsername2(String username){
        log.info("======== existByUsername ==============");
        return userRepositoryCustom.existByUsername(username);
    }

    // 아이디 중복 체크
    public User existByUsername(JoinUserDto joinUserDto){
        log.info("======== UserService - existByUsername ==============");
        String EmailAddr = joinUserDto.getEmail_id() + joinUserDto.getEmail_domain();
        joinUserDto.setEmail_addr(EmailAddr);
        return userRepositoryCustom.findUserByUsername(joinUserDto.getUsername());
    }

    // 회원 조회 - idx 로 조회
    @Transactional
    public User userDetails(User user){
        return userRepositoryCustom.findUser(user.getId());
    }

    // 회원조회 - 이름으로 조회
    @Transactional
    public User userDetailsByUsername(String username){
        return userRepositoryCustom.findUserByUsername(username);
    }

    // 비밀번호 조회
    @Transactional
    public boolean isMyPassword(String nowPwd, String username){
        return encoder.matches(nowPwd, userRepositoryCustom.findPasswordByUsername(username));
    }

    // 회원 비밀번호 수정
    @Transactional
    public void updatePwd(ModifyUserPwdDto modifyUserPwdDto, String username){
        String rawPwd = modifyUserPwdDto.getNewPw();
        String encPwd = encoder.encode(rawPwd);
        userRepositoryCustom.updateUserPwd(encPwd, username);
    }

    // 회원주소 수정
    @Transactional
    public void addrModify(ModifyUserDto modifyUserDto, String username){
        log.info("=====  addrModify called........ =====");
        log.info("username = {}", username);
        User findUser = userRepositoryCustom.findUserByUsername(username);
        int findUserId = findUser.getId();
        log.info("findUserId={}", findUserId);
        modifyUserDto.setUserIdx(findUserId);

        UserAddr findUserAddr = findUser.getUserAddr();
        log.info("\t findUserAddr = {}", findUserAddr);

        if (findUserAddr == null){
            log.info("------------ UserAddr Insert success -------------");
            UserAddr userAddr = new UserAddr();
            userAddr.setZipCode(modifyUserDto.getZipCode());
            userAddr.setPostAddr1(modifyUserDto.getPostAddr1());
            userAddr.setPostAddr2(modifyUserDto.getPostAddr2());
            userAddr.setUser(findUser);
            log.info("\t\t Update = {}", userAddr);
            addrRepository.save(userAddr);
        }else {
            log.info("------------ UserAddr Update success -------------");
            findUserAddr.setZipCode(modifyUserDto.getZipCode());
            findUserAddr.setPostAddr1(modifyUserDto.getPostAddr1());
            findUserAddr.setPostAddr2(modifyUserDto.getPostAddr2());
            findUserAddr.setUser(findUser);
            log.info("\t\t Update = {}", findUserAddr);
            addrRepository.save(findUserAddr);
        }
    }

    // 회원수정 - 이메일
    @Transactional
    public void emailModify(ModifyUserEmailDto modifyUserEmailDto, String username){
        String modifiedEmail = modifyUserEmailDto.getModifiedEmailAddr();
        User user = userRepositoryCustom.findUserByUsername(username);
        int findUserId = user.getId();
        modifyUserEmailDto.setUserIdx(findUserId);
        userRepositoryCustom.emailSave(modifyUserEmailDto);
    }

    // 회원수정 - 프로필
    @Transactional
    public void profileModify(ModifyUserProfileDto modifyUserProfileDto, String username) throws IOException {
        log.info("======= UserService - profileModify ===========");
        String fullPath = profileUploadService.getFullPath(modifyUserProfileDto.getProfileImgName());
        String profileFolderDir = getProfileDir(fullPath);
        log.info("\t\t profileFolderDir = {}", profileFolderDir);


        log.info("\t\t fullPath {}", fullPath);
        /*String originalFileName = modifyUserProfileDto.getProfileImg().getOriginalFilename();
        String storeFileName = modifyUserProfileDto.getProfileImgName();*/
        User user = userRepositoryCustom.findUserByUsername(username);

        log.info("\t ModifyUserProfileDto = {}", modifyUserProfileDto);

        UserProfile findUserProfile = user.getUserProfile();
        log.info("findUserProfile = {}", findUserProfile);

        if (findUserProfile == null){
            log.info("\t userProfile insert success");
            UserProfile userProfile1 = new UserProfile();
            userProfile1.setFileName(modifyUserProfileDto.getProfileImgName());
//            userProfile1.setFileUrl(fullPath);
            userProfile1.setFileUrl(profileFolderDir);
            userProfile1.setUser(user);
            userProfile1.setLocalDateTime(LocalDateTime.now());
            user.setNickName(modifyUserProfileDto.getNickName());
            userRepository.save(user);
            profileRepository.save(userProfile1);
        }else {
            log.info("\t userProfile update success");

//            UserProfile findUserProfile1 = findUserProfile.getUser().getUserProfile();
            findUserProfile.setFileName(modifyUserProfileDto.getProfileImgName());
            findUserProfile.setLocalDateTime(LocalDateTime.now());
//            findUserProfile.setFileUrl(fullPath);
            findUserProfile.setFileUrl(profileFolderDir);
            user.setNickName(modifyUserProfileDto.getNickName());
            userRepository.save(user);
            profileRepository.save(findUserProfile);
        }

    }
    
    // 회원 수정 - 관심 스킬
    @Transactional
    public void skillTagModify(List<Long> skillIdxes, String username) {
    	log.info("===== userService ========");
    	log.info(" \t ===== skillTagModify ========");
    	User findUser = userRepositoryCustom.findUserByUsername(username);
    	log.info("findUser = " + findUser.getId());
    	
    	List<User_Skilltag> findUserSkilltags = findUser.getUser_skillTag();
    	for(User_Skilltag findUserSkilltag : findUserSkilltags) {
    		log.info("findUserSkilltag = " + findUserSkilltag);
    	}

    	// 스킬태그 등록안한 경우
    	if(findUserSkilltags == null) {
    		log.info("\t\t ======= skillTagModify insert ======");
    		for(Long skillIdx : skillIdxes) {
    			SkillTagEntity findSkillTag = skillTagRepository.findById(skillIdx).orElseThrow(
    						() -> new EntityNotFoundException("해당 스킬은 존재하지 않습니다")
    					);
    			
    			User_Skilltag user_Skilltag = User_Skilltag.builder()
        				.user(findUser)
        				.skillTag(findSkillTag)
        				.build();
    			
    			user_skillTagRepository.save(user_Skilltag);
    		}
    	}else {
    		log.info("\t\t ======= skillTagModify modify ======");
    		
    		for(Long skillIdx : skillIdxes) {
    			SkillTagEntity findSkillTag = skillTagRepository.findById(skillIdx).orElseThrow(
    						() -> new EntityNotFoundException("해당 스킬은 존재하지 않습니다")
    					);
    			
    			for(User_Skilltag findUser_skillTag : findUserSkilltags) {
    				findUser_skillTag.modifySkill(findSkillTag);
    				user_skillTagRepository.save(findUser_skillTag);
    			}
    		}
    	}
    	
    }

    // 디렉토리만 추출
    private String getProfileDir(String fullPath){
        int pos = fullPath.lastIndexOf("\\");
        return fullPath.substring(0, pos);
    }

    // 로그인 userProfile 이미지 파일 이름 조회
    @Transactional
    public UserProfileDto getProfileFileName(String loginUsername){

        User loginUser = userRepository.findByUsername(loginUsername).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저는 존재하지 않습니다")
        );

        UserProfileDto response = new UserProfileDto();
        response.setFileName(loginUser.getUserProfile().getFileName());

        return response;
    }
}


