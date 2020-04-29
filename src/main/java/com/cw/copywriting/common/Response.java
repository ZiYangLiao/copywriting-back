package com.cw.copywriting.common;

public class Response<T> {
    private Integer code = 200;
    private String msg = "success";
    private T data = null;


    private Response(T data2) {
        this.data = data2;
    }

    private Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> of(T data) {
        return new Response<>(data);
    }

    public static <T> Response<T> of(Integer code, String msg) {
        return new Response(code, msg);
    }

    public static <T> Response<T> of(Integer code, String msg, T data) {
        return new Response(code, msg, data);
    }

    public static <T> Response<T> success() {
        return new Response<>(null);
    }

    public static <T> Response<T> fail(String msg) {
        return new Response<>(0, msg);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}