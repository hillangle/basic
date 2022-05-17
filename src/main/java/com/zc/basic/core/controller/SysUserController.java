package com.zc.basic.core.controller;

import com.zc.basic.common.domain.dto.Result;
import com.zc.basic.common.utils.ResultUtils;
import com.zc.basic.core.domain.SysUser;
import com.zc.basic.core.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/detail")
    public ResultUtils addUser(@RequestBody SysUser sysUser){
        boolean flag = sysUserService.saveSysUser(sysUser);
        if(flag){
            ResultUtils.ok();
        }
        return ResultUtils.ok();
    }
}
