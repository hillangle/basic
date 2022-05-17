package com.zc.basic.auth.mapper;

import com.zc.basic.auth.domain.AuthUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthUserMapper {

    /**
     * 根据登录名获取用户信息
     */
    AuthUser queryUserByAccount(String account) throws Exception;
}
