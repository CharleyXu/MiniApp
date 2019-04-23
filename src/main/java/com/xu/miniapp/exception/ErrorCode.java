package com.xu.miniapp.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS(1, "Success"),
    SYSTEM_ERROR(0, "系统内部异常"),
    CONNECTION_ERROR(0, "连接超时"),
    WX_API_ERROR(0, "微信第三方API错误"),
    PARAM_ILLEGAL(0, "参数不合法");

    private Integer code;
    private String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
