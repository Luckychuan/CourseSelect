package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Luckychuan on 2018/3/8.
 */

public class AttendanceCheck implements Serializable {

    @SerializedName("XM")
    private String name;
    @SerializedName("XH")
    private String account;
    @SerializedName("ZT")
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendanceCheck{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
