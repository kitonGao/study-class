package com.study.core.base.tips;

/**
 * 操作失败返回前台错误
 */
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message){
        super();
        this.code= code;
        this.message = message;
    }

}
