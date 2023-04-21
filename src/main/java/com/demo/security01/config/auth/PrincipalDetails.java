package com.demo.security01.config.auth;

import com.demo.security01.entity.User;
import com.demo.security01.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 시큐리티가 '/login' 주소 요청이 오면 낚아채서 로그인 진행
// 로그인 진행 완료 -> 시큐리티 session 을 만들어 넣어줌 (Security ContextHolder)
// Security ContextHolder 에 들어갈 수 있는 객체는 정해져 있음(Authentication 객체)
// Authentication 객체 안에 User 정보가 있어야 됨
// User 오브젝트 타입 정해져 있음 -> UserDetails 타입 객체

// Security Session -> Authentication 객체 -> UserDetails 타입이어야 함
// PrincipalDetails implements UserDetails 하면 PrincipalDetails 의 타입은 UserDetails 가 됨


public class PrincipalDetails implements UserDetails {

    private User user; // 컴포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 유저의 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
           /* @Override
            public String getAuthority() {
                return null;
            }*/

            // ENUM 타입을 사용한 경우 ENUM 을 toString() 으로 변환한 뒤 권한 지정
            @Override
            public String getAuthority() {
                return user.getRole().getAuthority();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {

        // 사이트에서 1년 동안 회원이 로그인을 안하면 휴면계정 전환하는 경우
        // 현재시간 - 로그인시간 => 1년을 초과하면 return false;
//        user.getLoginDate();// 해당 user 의 로그인 시간 가져옴

        return true;
    }
}
