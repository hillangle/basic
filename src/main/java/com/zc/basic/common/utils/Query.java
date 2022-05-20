package com.zc.basic.common.utils;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //
    private int offset;
    // 每页条数
    private int limit;
    // 当前页数
    private int pageIndex;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        // 分页参数
        this.offset = Integer.parseInt(params.get("offset").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.pageIndex = params.get("pageIndex") == null ? 1 : Integer.parseInt(params.get("pageIndex").toString());
        this.put("offset", offset);
        this.put("page", (pageIndex-1) * limit);
        this.put("limit", limit);
    }

}