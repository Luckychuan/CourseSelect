package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2018/1/5.
 */

public class BaseBean<T> {

    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("msgcode")
    private int msgCode;
    private String error;
    private T data;

    public BaseBean(boolean isSuccess, int msgCode, String error, T data) {
        this.isSuccess = isSuccess;
        this.msgCode = msgCode;
        this.error = error;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "isSuccess=" + isSuccess +
                ", msgCode=" + msgCode +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
