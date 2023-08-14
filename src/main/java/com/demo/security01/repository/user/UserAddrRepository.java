package com.demo.security01.repository.user;

import com.demo.security01.entity.user.UserAddr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddrRepository extends JpaRepository<UserAddr, Long> {


}
