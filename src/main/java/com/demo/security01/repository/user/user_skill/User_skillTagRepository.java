package com.demo.security01.repository.user.user_skill;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.security01.entity.tag.User_Skilltag;
import com.demo.security01.entity.user.User;

@Repository
public interface User_skillTagRepository extends JpaRepository<User_Skilltag, Long>, User_skillTagRepositoryCustom{
	
	Optional<User_Skilltag> findByUser(User user);

}
