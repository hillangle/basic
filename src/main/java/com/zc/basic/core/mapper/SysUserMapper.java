package com.zc.basic.core.mapper;

import com.zc.basic.core.domain.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

    /**
     * 插入用户
     */
    void save(SysUser sysUser) throws Exception;
}
