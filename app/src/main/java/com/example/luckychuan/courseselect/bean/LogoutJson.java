package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/12/12.
 */

public class LogoutJson {

    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("msgcode")
    private int msgCode ;
    private String error;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
