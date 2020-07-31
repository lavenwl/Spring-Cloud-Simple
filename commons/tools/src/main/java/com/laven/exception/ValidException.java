package com.laven.exception;

public class ValidException extends BaseException{

    public ValidException() {super();}

    public ValidException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ValidException (String code, String msg) {
        super();
        this.msg = msg;
        this.code = code;
    }
}
