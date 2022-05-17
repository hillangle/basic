package com.zc.basic.core.domain;

import com.zc.basic.auth.domain.AuthUser;
import lombok.Data;

/**
 * 系统用户实体
 * @Param unid 主键
 * @Param userName 用户名
 * @Param account 登录名
 * @Param password 密码
 * @Param userRole 所属权限
 * @Param sex 性别 0-未知 1-男 2-女
 * @Param mobile 手机号
 * @Param idCardNo 身份证号
 * @Param status 用户状态 Y-启用 N-禁用
 * @Param delFlag 删除状态 Y-已删除 N-未删除
 */
@Data
public class SysUser extends AuthUser {
    /**
     * 性别 0-未知 1-男 2-女
     */
    private String sex;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 身份证号
     */
    private String idCardNo;
    /**
     * 用户状态 Y-启用 N-禁用
     */
    private String status;
    /**
     * 删除状态 Y-已删除 N-未删除
     */
    private String delFlag;
}
