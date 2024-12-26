package com.demo.security01.config.security.auth;

import com.demo.security01.entity.user.User;
import com.demo.security01.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Authentication 객체를 만드는 역할
// 시큐리티 설정에서 .loginProcessUrl("/login")
// '/login' 요청이 오면 자동으로 UserDetailService 타입으로 IoC 되어 있는
// loadByUsername 함수가 실행됨

@Slf4j
//@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;


    // 로그인
    // 정상적으로 리턴되는 경우,
    // 시큐리티 session (내부 Authentication(내부 UserDetails))

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username)
                .orElseThrow(()->{
                   return new UsernameNotFoundException(username + " " + messageSourceAccessor.getMessage("error.usernameNotFound.username"));
                });

        return new PrincipalDetails(userEntity); // 시큐리티 세션에 유저 정보 저장됨
       /* if (username != null){
            log.info("{}", userEntity);
            return new PrincipalDetails(userEntity);
        } else {
            return new IllegalAr
        }*/
    }
}
