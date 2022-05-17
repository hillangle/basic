package com.zc.basic.auth.domain;

import lombok.Data;

/**
 * 用户登录实体
 * @Param unid 主键
 * @Param userName 用户名
 * @Param account 登录名
 * @Param password 密码
 * @Param userRole 所属权限
 */
@Data
public class AuthUser {
    /**
     * 主键
     */
    private Integer unid;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限
     */
    private String userRole;
}
