package com.zc.basic.common.service;

import com.zc.basic.common.utils.PageUtils;
import com.zc.basic.common.utils.Query;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Service
@RestControllerAdvice
public class BasicService<T> {

    public PageUtils queryPages(Query query){
        return new PageUtils(null, 0);
    }
}
