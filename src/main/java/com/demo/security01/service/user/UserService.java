package com.demo.security01.service.user;

import com.demo.security01.config.exception.UserNotMatchException;
import com.demo.security01.entity.redis.TempPw;
import com.demo.security01.entity.tag.SkillTagEntity;
import com.demo.security01.entity.tag.User_Skilltag;
import com.demo.security01.entity.user.User;
import com.demo.security01.entity.user.UserAddr;
import com.demo.security01.entity.user.UserProfile;
import com.demo.security01.model.Role;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.model.dto.user.JoinUserDto;
import com.demo.security01.model.dto.user.UserProfileDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserEmailDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserProfileDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserPwdDto;
import com.demo.security01.repository.redis.RedisRepository;
import com.demo.security01.repository.study.study_skill.SkillTagRepository;
import com.demo.security01.repository.test.MultipartFileTest.RedisTestRepository;
import com.demo.security01.repository.user.UserAddrRepository;
import com.demo.security01.repository.user.UserProfileRepository;
import com.demo.security01.repository.user.UserRepository;
import com.demo.security01.repository.user.UserRepositoryCustom;
import com.demo.security01.repository.user.user_skill.User_skillTagRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
    private final MailServiceImpl mailServiceImpl;
//    private final RedisTemplate<String, TempPw> redisTemplate;
    private final RedisRepository redisRepository;
    private final JavaMailSender emailSender;
    

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
        
        
        // 회원가입시 관심 스킬 입력한 경우
        if(joinUserDto.getSkillTagIdx() != null && !joinUserDto.getSkillTagIdx().isEmpty()) {
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
    public User userDetails(User user){
        return userRepositoryCustom.findUser(user.getId());
    }

    // 회원조회 - 이름으로 조회
    public User userDetailsByUsername(String username){
        return userRepositoryCustom.findUserByUsername(username);
    }

    // 비밀번호 조회
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
    	log.info("findUserSkilltags = " + findUserSkilltags);

    	// 빈 배열은 배열 자체 객체는 존재하는것, null 이 아님에 유의
    	// 스킬태그 등록안한 경우
    	if(findUserSkilltags == null || findUserSkilltags.isEmpty()) {
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
    	}else if(findUserSkilltags != null && !findUserSkilltags.isEmpty()){
    		log.info("\t\t ======= skillTagModify modify ======");
    		
    		user_skillTagRepository.user_skillTagDeleteByUserIdx(findUser);
    		
    		
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
    	}
    	
    }
    
    // 회원 관심 스킬 추천 조회
    @Transactional(readOnly = true)
    public List<StudyResponseDto> recommendStudy(){
    	return null;
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
    
    // 유저조회 - 이메일
    public User getUserByEmail(String email) {
    	User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("해당 유저 없음"));
    	return user;
    }
    
    // 비밀번호 찾기 
    
    // 방안 2. 비밀번호 재설정 링크 전송(일단은 test)
    @Transactional
    public void sendResetPwLink(String userEmail) throws Exception {
    	
    	// 유저확인
    	User findUser = userRepository.findByEmail(userEmail).orElseThrow(
    			 () -> new EntityNotFoundException("메일을 다시 확인해주세요"));
    	
    	// 이메일(이미 유저확인에서 매개변수로 들어온 이메일로 유저를 찾았는데 그 유저의 이메일을 찾는게 맞는것인가)
    	String email = findUser.getEmail();
    	
    	log.info("email = " + email);
    	
    	// 랜덤 토큰 생성
    	String resetPwToken = UUID.randomUUID().toString();
    	log.info("resetPwToken = " + resetPwToken);
    	
    	// redis 에 저장
    	TempPw tempPw = new TempPw(resetPwToken, email);
    	log.info("tempPw = " + tempPw);
    	redisRepository.save(tempPw);
    	
    	// 이메일 전송
    	String pwResetLink = "http://localhost:8060/user/resetPw?tempToken=" + resetPwToken;
//    	MimeMessage resetLink = mailServiceImpl.createResetPwEmail(email, pwResetLink);
    	
    	mailServiceImpl.sendResetPwLink(email, pwResetLink);
    	
    }
    	
    
    // 토큰 확인
    public boolean validateToken(String tempToken) {
//    	TempPw findTemp = redisRepository.findById(token).orElseThrow(() -> new EntityNotFoundException("해다아 토큰 존재안함"));
    	
    	return redisRepository.findById(tempToken).isPresent();
    }
    
    // 비밀번호 재설정시 비밀번호 변경
    @Transactional
    public void changePw(String tempToken, ModifyUserPwdDto pwdDto) {
    	
    	// 유저 확인
    	TempPw redis = redisRepository.findById(tempToken).get();
    	String email = redis.getEmail();
    	
    	User findUser = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("이메일을 다시 확인해주세요"));
    	
    	updatePwd(pwdDto, findUser.getUsername());
    }
    
    
    // 방안 1. 임시 비밀번호 발급 및 전송
	/*
	 * @Transactional public void sendTempPw(String username, String email) {
	 * 
	 * // 유저 확인 User findUser = userRepository.findByUsername(username).orElseThrow(
	 * () -> new EntityNotFoundException(username + " 님은 존재하지 않음") );
	 * 
	 * // 이메일 일치하는지 if(findUser.getEmail().equals(email)) { throw new
	 * RuntimeException("이메일 다시 확인해주세요"); }
	 * 
	 * // 임시 비번 생성 // 
	 * 
	 * 
	 * // 임시 비번 저장(redis)
	 * 
	 * // 임시 비번 저장(db)
	 * 
	 * // 이메일 전송
	 * 
	 * 
	 * 
	 * }
	 */
}


