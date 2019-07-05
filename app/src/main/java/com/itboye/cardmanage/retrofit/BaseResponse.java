package com.itboye.cardmanage.retrofit;

public class BaseResponse<T> {
    /**
     * Created by goldze on 2017/5/10.
     * 该类仅供参考，实际业务返回的固定字段, 根据需求来定义，
     */
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return code.equals("0");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
