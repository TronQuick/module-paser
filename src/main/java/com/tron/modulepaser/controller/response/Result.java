package com.tron.modulepaser.controller.response;

import lombok.Data;

/**
 * Title:       Result
 * Description: 接口数据返回格式
 *
 */

@Data
public class Result {

    private int code;
    private String msg;
    private Object data;
//    private T data;

    public Result(ResultCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

//    public Result(ResultCode resultCode, final T data) {
//        this(resultCode);
//        this.data = data;
//    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

}
