package com.laven.exception;

public class BaseException extends RuntimeException{
    protected String code;
    protected String msg;

    public BaseException() {super();}

//    这两个构造器的具体作用
    public BaseException(String msg, String code) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
