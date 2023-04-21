package com.demo.security01.repository;


import com.demo.security01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepositoryCustom {

    List<User> findAllUser();

    User findUser(int id);
}
