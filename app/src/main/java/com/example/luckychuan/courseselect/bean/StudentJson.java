package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Luckychuan on 2017/11/30.
 */

public class StudentJson{
    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("msgcode")
    private int msgCode ;
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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

    @Override
    public String toString() {
        return "StudentJson{" +
                "isSuccess=" + isSuccess +
                ", msgCode=" + msgCode +
                ", data=" + data +
                '}';
    }

    public class Data implements Serializable{
        @SerializedName("Lingpai")
        private String userKey;
        @SerializedName("xueHao")
        private String id;
        @SerializedName("xingMing")
        private String name;
        @SerializedName("xingBie")
        private String sex;
        @SerializedName("xueYuan")
        private String academy;
        @SerializedName("zhuanYe")
        private String major;
        @SerializedName("nianJi")
        private String grade;
        @SerializedName("banJi")
        private String studentClass;
        @SerializedName("miMa")
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAcademy() {
            return academy;
        }

        public void setAcademy(String academy) {
            this.academy = academy;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getStudentClass() {
            return studentClass;
        }

        public void setStudentClass(String studentClass) {
            this.studentClass = studentClass;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "userKey='" + userKey + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", academy='" + academy + '\'' +
                    ", major='" + major + '\'' +
                    ", grade='" + grade + '\'' +
                    ", studentClass='" + studentClass + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }



}
