package com.laven.api;

public class R<T> {
    private int code;
    private String msg;
    private T data;

    public R() { }

    public R(Builder<T> builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class Builder<T> {
        private int code;
        private String msg;
        private T data;

        public Builder() { }

        public R buildCustomize(int code, String msg) {
            this.code = code;
            this.msg = msg;
            return new R(this);
        }

        public R buildSuccess() {
            this.code = 200;
            this.msg = "success";
            return new R(this);
        }

        public R buildFailed() {
            this.code = -1;
            this.msg = "failed";
            return new R(this);
        }

        public R buildFailed(String msg) {
            this.code = -1;
            this.msg = msg;
            return new R(this);
        }

        public Builder setData(T data) {
            this.data = data;
            return this;
        }
    }
}
