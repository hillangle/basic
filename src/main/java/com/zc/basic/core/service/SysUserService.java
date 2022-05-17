package com.zc.basic.core.service;

import com.zc.basic.common.exception.BusinessException;
import com.zc.basic.common.exception.enums.BusinessExceptionEnum;
import com.zc.basic.core.domain.SysUser;
import com.zc.basic.core.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Service
@RestControllerAdvice
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public boolean saveSysUser(SysUser sysUser) {
        try {
            sysUserMapper.save(sysUser);
            if (sysUser.getUnid() != null) {
                return true;
            }else{
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_SAVE_EXCEPTION.getCode(), BusinessExceptionEnum.BUSINESS_SAVE_EXCEPTION.getMsg());
            }
        } catch (Exception e){
            e.printStackTrace();
            log.error(BusinessExceptionEnum.BUSINESS_SAVE_EXCEPTION.getCode(), e.getMessage());
            return false;
        }
    }

}
