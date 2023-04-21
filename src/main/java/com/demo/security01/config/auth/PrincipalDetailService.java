package com.demo.security01.config.auth;

import com.demo.security01.entity.User;
import com.demo.security01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Authentication 객체를 만드는 역할
// 시큐리티 설정에서 .loginProcessUrl("/login")
// '/login' 요청이 오면 자동으로 UserDetailService 타입으로 IoC 되어 있는
// loadByUsername 함수가 실행됨

@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 로그인
    // 정상적으로 리턴되는 경우,
    // 시큐리티 session (내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);

        if (username != null){
            log.info("{}", userEntity);
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
