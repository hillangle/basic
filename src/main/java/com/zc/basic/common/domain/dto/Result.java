package com.zc.basic.common.domain.dto;

import lombok.Data;

@Data
public class Result {
    /**
     * 接口调用状态码
     */
    public String resultCode;
    /**
     * 接口调用结果描述
     */
    public String resultMsg;
    /**
     * 接口调用返回参数
     */
    public String resultData;
    /**
     * http状态码
     */
    public int httpCode;

    public String toJSONString() {
        return "{" +
                "\"resultCode\":\"" + resultCode + "\"" +
                ", \"resultMsg\":\"" + resultMsg + "\"" +
                ", \"resultData\":\"" + resultData + "\"" +
                ", \"httpCode\":\"" + httpCode + "\"" +
                "}";
    }
}
