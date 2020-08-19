package com.bitfs.client.model;

public class CommonResponse<T> {

    public CommonResponse() {
    }

    public CommonResponse(String return_code, String msg) {
        this.return_code = return_code;
        this.msg = msg;
    }

    public CommonResponse(String return_code, String msg, T result) {
        this.return_code = return_code;
        this.msg = msg;
        this.result = result;
    }

    private String return_code="SUCCESS";

    private String msg="success";

    private T result;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
