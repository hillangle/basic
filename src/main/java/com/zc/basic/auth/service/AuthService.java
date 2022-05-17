package com.zc.basic.auth.service;

import com.zc.basic.auth.domain.AuthUser;
import com.zc.basic.auth.mapper.AuthUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RestControllerAdvice
public class AuthService implements UserDetailsService {

    @Autowired
    private AuthUserMapper authUserMapper;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            AuthUser sysUser = authUserMapper.queryUserByAccount(s);
            if (sysUser == null) {
                return null;
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(sysUser.getUserRole()));
            //封装 SpringSecurity  需要的UserDetails 对象并返回
            UserDetails userDetails = new User(sysUser.getAccount(), sysUser.getPassword(), authorities);
            return userDetails;
        } catch(Exception e){
            log.error("根据用户名获取用户信息失败,失败原因:{}",e);
            return null;
        }
    }

    public AuthUser queryUserByAccount(String s) {
        try {
            AuthUser sysUser = authUserMapper.queryUserByAccount(s);
            if (sysUser == null) {
                return null;
            }
            return sysUser;
        } catch(Exception e){
            log.error("根据用户名获取用户信息失败,失败原因:{}",e);
            return null;
        }
    }
}
