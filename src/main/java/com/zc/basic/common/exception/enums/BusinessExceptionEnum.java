package com.zc.basic.common.exception.enums;

public enum BusinessExceptionEnum {
    /**
     * 业务数据插入异常
     */
    BUSINESS_SAVE_EXCEPTION("500", "数据插入异常"),
    BUSINESS_UPDATE_EXCEPTION("500", "数据修改异常"),
    BUSINESS_AUTH_EXCEPTION("403", "用户鉴权异常");

    /**
     * 状态
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    private BusinessExceptionEnum(String code, String msg ){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
