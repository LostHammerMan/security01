package com.demo.security01.config.auth;

import com.demo.security01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Authentication 객체를 만드는 역할
// 시큐리티 설정에서 .loginProcessUrl("/login")
// login 요청이 오면 자동으로 UserDetailService 타입으로 IoC 되어 있는
// loadByUsername 함수가 실행됨

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
