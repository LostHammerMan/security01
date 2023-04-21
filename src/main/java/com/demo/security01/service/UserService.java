package com.demo.security01.service;

import com.demo.security01.entity.User;
import com.demo.security01.model.Role;
import com.demo.security01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void save(User user){

        String rawPwd = user.getPassword();
        String encPwd = encoder.encode(rawPwd);
        user.setPassword(encPwd);
        user.setRole(Role.ADMIN);
        log.info("user = {}", user);
        userRepository.save(user);

    }
}


