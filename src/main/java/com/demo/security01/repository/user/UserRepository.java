package com.demo.security01.repository.user;

import com.demo.security01.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // @Repository 없어도 IoC 됨, JPARepository 상속 받기 때문
public interface UserRepository extends JpaRepository<User, Integer>/*, UserRepositoryCustom */{
    // findBy 규칙 -> Username 문법
    // select * from user where username = 1?(첫번째 파라미터)
    Optional<User> findByUsername(String username);

    // select * from user where email = 1?
    public Optional<User> findByEmail(String email);


}
