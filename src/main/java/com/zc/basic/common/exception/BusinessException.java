package com.zc.basic.common.exception;

import lombok.Data;

/**
 * 业务异常封装
 */
@Data
public class BusinessException extends Exception{
    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BusinessException(){
        super();
    }

    public BusinessException(String code,String message){
        super(message);
        this.errorCode = code;
        this.errorMsg = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
