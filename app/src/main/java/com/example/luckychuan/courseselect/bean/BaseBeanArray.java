package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class BaseBeanArray<T> {

    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("msgcode")
    private int msgCode;
    private String error;
    private T[] datas;

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

    public T[] getDatas() {
        return datas;
    }

    public void setDatas(T[] datas) {
        this.datas = datas;
    }
}
