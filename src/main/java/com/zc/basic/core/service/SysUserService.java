package com.zc.basic.core.service;

import com.zc.basic.common.exception.BusinessException;
import com.zc.basic.common.exception.enums.BusinessExceptionEnum;
import com.zc.basic.common.service.BasicService;
import com.zc.basic.common.utils.PageUtils;
import com.zc.basic.common.utils.Query;
import com.zc.basic.core.domain.SysUser;
import com.zc.basic.core.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@Service
@RestControllerAdvice
public class SysUserService extends BasicService<SysUser> {

    @Autowired
    private SysUserMapper sysUserMapper;

    public boolean saveSysUser(SysUser sysUser) throws BusinessException {
        try {
            sysUserMapper.save(sysUser);
            if (sysUser.getUnid() != null) {
                return true;
            }else{
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_SAVE_EXCEPTION.getCode(), BusinessExceptionEnum.BUSINESS_SAVE_EXCEPTION.getMsg());
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean updateSysUser(SysUser sysUser) throws BusinessException {
        try {
            sysUserMapper.update(sysUser);
            if (sysUser.getUnid() != null) {
                return true;
            }else{
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_UPDATE_EXCEPTION.getCode(), BusinessExceptionEnum.BUSINESS_UPDATE_EXCEPTION.getMsg());
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    public PageUtils queryPages(Query query){
        List<SysUser> sysUsers = sysUserMapper.queryPages(query);
        int total = sysUserMapper.count(query);
        return new PageUtils(sysUsers, total);
    }

}
