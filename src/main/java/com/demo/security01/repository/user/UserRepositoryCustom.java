package com.demo.security01.repository.user;


import com.demo.security01.entity.user.User;
import com.demo.security01.entity.user.UserAddr;
import com.demo.security01.entity.user.UserProfile;
import com.demo.security01.model.dto.admin.AdminUpdateDto;
import com.demo.security01.model.dto.paging.Criteria;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserEmailDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserProfileDto;
import com.querydsl.core.BooleanBuilder;

import java.util.List;


public interface UserRepositoryCustom {


    // 전체 회원 조회 + 페이징
    List<User> findAllUser(Criteria cri);

    // 전체 회원 조회
    List<User> findAllUserList();

    // 전체 회원수
    int userListCnt(Criteria cri);

    // BooleanBuilder
    BooleanBuilder searchCondition(Criteria cri);

    User findUser(int id);

    User findUserByUsername(String name);

    // 관리자 - 회원수정
    void updateUser(AdminUpdateDto dto);

    // 관리자 회원 삭제
    void delete(int id);

    void deleteAll(List<Integer> id);

    // 회원 중복 체크
    boolean existByUsername(String username);

    // 회원주소수정
    public void addrSave(ModifyUserDto modifyUserDto);

    // 회원메일수정
    public void emailSave(ModifyUserEmailDto modifyUserEmailDto);

    // 회원비밀번호 조회
    public String findPasswordByUsername(String username);

    // 회원비밀번호 수정
    public void updateUserPwd(String pwd, String username);

    // 회원프로필 수정
    public void profileSave(ModifyUserProfileDto modifyUserProfileDto);

    public void addrSave2(UserAddr userAddr);

    public List<UserProfile> getOldProfileFile();
}
