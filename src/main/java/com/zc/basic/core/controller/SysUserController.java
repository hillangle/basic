package com.zc.basic.core.controller;

import com.zc.basic.common.exception.BusinessException;
import com.zc.basic.common.exception.enums.BusinessExceptionEnum;
import com.zc.basic.common.utils.PageUtils;
import com.zc.basic.common.utils.Query;
import com.zc.basic.common.utils.ResultUtils;
import com.zc.basic.core.domain.SysUser;
import com.zc.basic.core.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/addUser")
    public ResultUtils addUser(@RequestBody SysUser sysUser) {
        try {
            boolean flag = sysUserService.saveSysUser(sysUser);
            if (flag) {
                return ResultUtils.ok();
            } else {
                return ResultUtils.error();
            }
        }catch (BusinessException b){
            return ResultUtils.error(BusinessExceptionEnum.BUSINESS_SAVE_EXCEPTION.getCode(),BusinessExceptionEnum.BUSINESS_SAVE_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/updateUser")
    public ResultUtils updateUser(@RequestBody SysUser sysUser) {
        try {
            boolean flag = sysUserService.updateSysUser(sysUser);
            if (flag) {
                return ResultUtils.ok();
            } else {
                return ResultUtils.error();
            }
        }catch (BusinessException b){
            return ResultUtils.error(BusinessExceptionEnum.BUSINESS_UPDATE_EXCEPTION.getCode(),BusinessExceptionEnum.BUSINESS_UPDATE_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/queryUserPage")
    public ResultUtils queryUserPage(@RequestBody Map<String, Object> params) {
        Query query = new Query(params);
        PageUtils pageUtils = sysUserService.queryPages(query);
        return ResultUtils.ok(pageUtils,query);
    }
}
