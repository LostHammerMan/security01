package com.demo.security01.service.community;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.security01.config.exception.LoungeNotFountException;
import com.demo.security01.config.exception.UserNotMatchException;
import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.BoardType;
import com.demo.security01.model.dto.community.LoungeListResponseDto;
import com.demo.security01.model.dto.community.LoungeModifyRequest;
import com.demo.security01.model.dto.community.LoungeWriteRequest;
import com.demo.security01.repository.boardLike.LikeRepository;
import com.demo.security01.repository.category.CategoryRepository;
import com.demo.security01.repository.comment.CommentRepository;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoungeService {


    private final LoungeRepository loungeRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    // 게시글 저장
    @Transactional
    public void loungeSave(LoungeWriteRequest request, String username) {

        User findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 정보를 찾을 수 없습니다"));

        CategoryEntity category = categoryRepository.findById(request.getCateCode())
                .orElseThrow(() -> new RuntimeException("해당 카테고리 없음"));

        LoungeEntity entity = LoungeEntity.builder()
                .title(request.getTitle())
                .content(request.getContents())
                .cateCode(category)
//                .regDate(LocalDateTime.now())
                .user(findUser)
                .build();

        loungeRepository.save(entity);

    }

    // 라운지 수정
    @Transactional
    public void loungeModify(LoungeModifyRequest request, String username) {

        User loginUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 없음"));

        log.info("request = {}", request);
        LoungeEntity findLounge = loungeRepository.findById(request.getLoungeId())
                .orElseThrow(() -> new LoungeNotFountException());

        if (loginUser != findLounge.getUser()) {
            throw new UserNotMatchException();
        }

        log.info("findLounge = {}", findLounge);

        CategoryEntity findCateCode = null;

        if (request.getCateCode() != null) {
            findCateCode = categoryRepository.findById(request.getCateCode())
                    .orElseThrow(() -> new RuntimeException("해당 카테고리는 존재 하지 않음"));
        } else {
            findCateCode = findLounge.getCateCode();
        }

        findLounge.loungeEdit(request, findCateCode);
        log.info("findLounge = {}", findLounge);

//            LoungeEntity modifyLounge = findLounge.builder()
//                    .title(request.getTitle())
//                    .content(request.getContents())
//                    .cateCode(findCateCode)
//                    .updateDate(request.getUpdateDate())
//                    .build();

//        log.info("modifyLounge = {}", modifyLounge);
//        loungeRepository.save(modifyLounge);
    }

    // 라운지 삭제
    @Transactional
    public void loungeDelete(Long loungeId, String loginUsername) {

        User loginUser = userRepository.findByUsername(loginUsername)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저는 존재하지 않습니다."));

        LoungeEntity findLounge = loungeRepository.findById(loungeId)
                .orElseThrow(() -> new LoungeNotFountException());

        // 작성유저와 로그인 유저가 다른 경우
        if (loginUser != findLounge.getUser()) {
            throw new UserNotMatchException();
        }

        loungeRepository.deleteById(findLounge.getIdx());
    }

    // 라운지 조회
    @Transactional
    public LoungeEntity getLounge(Long id) {
        LoungeEntity findLounge = loungeRepository.findById(id)
                .orElseThrow(() -> new LoungeNotFountException());
        findLounge.setViewCount(findLounge.getViewCount() + 1);
//        loungeRepository.save(findLounge);
        return findLounge;
    }

    //
    public boolean isCheckLike(Long loungeId, String username) {
        boolean isLikeCheck = false;
        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저 없음")
        );

        LoungeEntity findLounge = loungeRepository.findById(loungeId).orElseThrow(
                () -> new LoungeNotFountException()
        );
        if (likeRepository.existsByUserAndLounge(findUser, findLounge)) {
            return isLikeCheck = true;
        } else {
            return isLikeCheck;
        }
    }

    ;


    // 라운지 목록
    @Transactional(readOnly = true)
    public List<LoungeEntity> findAllLounge() {
        return loungeRepository.findAll();
    }

    // 라운지 목록 + 페이징
    @Transactional(readOnly = true)
    public List<LoungeListResponseDto> getAllLoungeWithPaging(Long lastIdx) {
        log.info("==== loungeServiceCalled.. ====");
        log.info("\t\t getAllLoungeWithPaging called....");

        List<LoungeListResponseDto> dtos = new ArrayList<>();
        if (lastIdx == null) {
            Long maxId = loungeRepository.getMaxLoungeIdx();
            List<LoungeEntity> allLoungeWithPaging = loungeRepository.getAllLoungeWithPaging(maxId + 1, 9);
            entityToDto(dtos, allLoungeWithPaging);
            return dtos;
        }

        List<LoungeEntity> allLoungeWithPaging = loungeRepository.getAllLoungeWithPaging(lastIdx, 9);
        entityToDto(dtos, allLoungeWithPaging);
        return dtos;
    }

    private void entityToDto(List<LoungeListResponseDto> dtos, List<LoungeEntity> list) {
        for (LoungeEntity entity : list) {
            LoungeListResponseDto response = LoungeListResponseDto.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .username(entity.getUser().getUsername())
                    .categoryName(entity.getCateCode().getCategoryName())
                    .profileFilename(entity.getUser().getUserProfile().getFileName())
                    .regDate(entity.getRegDate())
                    .viewCount(entity.getViewCount())
                    .commentCount(commentRepository.getCommentListCount(entity.getIdx(), BoardType.LOUNGE))
                    .likeCount(entity.getLikeCount()).build();
            dtos.add(response);
        }
    }

    // 라운지 목록 + 페이징 + Slice활용
    public Slice<LoungeEntity> getAllLoungeWithPagingWithSlice(Long lastIdx, Pageable pageable) {

//        Pageable pageable = PageRequest.of(0, 9);
        return loungeRepository.getAllLoungeWithPaging2(lastIdx, pageable);

    }
}
