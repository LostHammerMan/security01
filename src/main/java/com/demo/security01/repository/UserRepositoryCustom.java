package com.demo.security01.repository;


import com.demo.security01.entity.User;
import com.demo.security01.model.dto.AdminUpdateDto;
import com.demo.security01.model.dto.DeleteDto;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepositoryCustom {

    List<User> findAllUser();

    User findUser(int id);

    // 관리자 - 회원수정
    void updateUser(AdminUpdateDto dto);

    // 관리자 회원 삭제
    void delete(int id);

    void deleteAll(List<Integer> id);
}
