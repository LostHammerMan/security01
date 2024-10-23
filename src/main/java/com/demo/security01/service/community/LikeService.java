package com.demo.security01.service.community;

import com.demo.security01.config.exception.LoungeNotFountException;
import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.BoardType;
import com.demo.security01.repository.boardLike.LikeRepository;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.study.StudyRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeService {

    private final LoungeRepository loungeRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final StudyRepository studyRepository;

    @Transactional
    public void addLike(Long boardId, String username, BoardType boardType){
    	
    	User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저 없음")
        );
    	
    	if(boardType == BoardType.LOUNGE) {
    		LoungeEntity findLounge = loungeRepository.findById(boardId).orElseThrow(
                    () -> new LoungeNotFountException()
            );
    		
    		if (!likeRepository.existsByUserAndLounge(findUser, findLounge)){
                findLounge.setLikeCount(findLounge.getLikeCount() + 1);
//                likeRepository.save(new BoardLike(findUser, findLounge));
                likeRepository.save(BoardLike.builder()
                		.user(findUser)
                		.lounge(findLounge)
                		.build());
    		}else {
    			findLounge.setLikeCount(findLounge.getLikeCount() - 1);
    			likeRepository.deleteByUserAndLounge(findUser, findLounge);
    		}
    	}else if(boardType == BoardType.STUDY) {
    		StudyEntity findStudy = studyRepository.findById(boardId).orElseThrow(
    				() -> new EntityNotFoundException(boardId + "번 게시글은 존재하지 않습니다.")
			);
    		
    		if (!likeRepository.existsByUserAndStudy(findUser, findStudy)){
                findStudy.setLikeCount(findStudy.getLikeCount() + 1);
                likeRepository.save(BoardLike.builder()
                		.user(findUser)
                		.study(findStudy)
                		.build());
    		}else {
    			findStudy.setLikeCount(findStudy.getLikeCount() - 1);
    			likeRepository.deleteByUserAndStudy(findUser, findStudy);
    		}
    		
    	}
        

        
        
        }/*else {*/
//            findLounge.setLikeCount(findLounge.getLikeCount() - 1);
//            likeRepository.deleteByUserAndLounge(user, findLounge);
//        }
    

    public void deleteLike(Long boardId, String username){
        LoungeEntity findLounge = loungeRepository.findById(boardId).orElseThrow(
                () -> new LoungeNotFountException()
        );

        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저 없음")
        );
        if (likeRepository.existsByUserAndLounge(findUser, findLounge)){
            findLounge.setLikeCount(findLounge.getLikeCount() - 1);
            likeRepository.deleteByUserAndLounge(findUser, findLounge);
        }
    }

    
    public int getLikeCount(Long boardId) {
        LoungeEntity findLounge = loungeRepository.findById(boardId).orElseThrow(
                () -> new LoungeNotFountException()
        );

        int likeCount = findLounge.getLikeCount();
        return likeCount;
    }
    
    public int getLikeCountStudy(Long studyIdx) {
    	StudyEntity findStudy = studyRepository.findById(studyIdx).orElseThrow(
    			() -> new EntityNotFoundException("해당 스터디/프로젝트는 존재하지 않습니다"));
    	
    	int likeCount = findStudy.getLikeCount();
    	
    	return likeCount;
    			
    }
}
