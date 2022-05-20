package com.zc.basic.core.mapper;

import com.zc.basic.common.exception.BusinessException;
import com.zc.basic.common.utils.Query;
import com.zc.basic.core.domain.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {

    /**
     * 插入用户
     */
    void save(SysUser sysUser);

    /**
     * 修改用户
     */
    void update(SysUser sysUser);

    /**
     * 分页查询用户列表
     */
    List<SysUser> queryPages(Query query);

    /**
     * 用户表数据量统计
     */
    int count(Query query);
}
