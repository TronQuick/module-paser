package com.tron.modulepaser.controller.response;

/**
 * Title:       ResultCode
 * Description: 状态码
 *
 */

public enum  ResultCode {

    SUCCESS(200, "请求成功"),
    WARN(500, "ERROR");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
