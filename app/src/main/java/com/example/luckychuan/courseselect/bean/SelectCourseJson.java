package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/12/12.
 */

public class SelectCourseJson {

    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("msgcode")
    private int msgCode ;
    private CourseJson[] data;

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

    public CourseJson[] getData() {
        return data;
    }

    public void setData(CourseJson[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SelectCourseJson{" +
                "isSuccess=" + isSuccess +
                ", msgCode=" + msgCode +
                ", data=" + data.toString() +
                '}';
    }
}

