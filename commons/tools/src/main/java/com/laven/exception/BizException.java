package com.laven.exception;

public class BizException extends BaseException {

    public BizException() {super();}

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String code, String msg) {
        super();
        this.msg = msg;
        this.code = code;
    }
}
