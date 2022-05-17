package com.zc.basic.auth.jwt;

import com.zc.basic.auth.domain.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtSysUser implements UserDetails {

    private Integer unid;
    private String username;
    private String account;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtSysUser(){}

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtSysUser(AuthUser user) {
        unid = user.getUnid();
        username = user.getUserName();
        password = user.getPassword();
        account = user.getAccount();
        authorities = Collections.singleton(new SimpleGrantedAuthority(user.getUserRole()));
    }

    public JwtSysUser(User user) {
        password = user.getPassword();
        account = user.getUsername();
        authorities = user.getAuthorities();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() { return username; }

    public String getAccount() { return account; }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

 @Override
 public String toString() {
  return "JwtUser{" +
    "unid=" + unid +
    ", userName='" + username + '\'' +
    ", account='" + account + '\'' +
    ", password='" + password + '\'' +
    ", authorities=" + authorities +
    '}';
 }
}
