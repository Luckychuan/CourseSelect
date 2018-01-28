package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public class StudentMyCourse {

    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("msgcode")
    private int msgCode ;
    private String error;
    private Data[] data;

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
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

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }



    public class Data{
        @SerializedName("KCBH")
        private String id;
        @SerializedName("KCMC")
        private String  name;
        @SerializedName("RKJS")
        private String teacher ;
        @SerializedName("SKZC")
        private String duration ;
        @SerializedName("WEEK")
        private int  week;
        @SerializedName("JC")
        private int time  ;
        @SerializedName("XQ")
        private String campus ;
        @SerializedName("CJ")
        private int score;
        @SerializedName("TP")
        private int rate;         //评教
        @SerializedName("ZT")
        private String commit;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCommit() {
            return commit;
        }

        public void setCommit(String commit) {
            this.commit = commit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getCampus() {
            return campus;
        }

        public void setCampus(String campus) {
            this.campus = campus;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", teacher='" + teacher + '\'' +
                    ", duration='" + duration + '\'' +
                    ", week=" + week +
                    ", time=" + time +
                    ", campus='" + campus + '\'' +
                    ", score=" + score +
                    ", rate=" + rate +
                    ", commit='" + commit + '\'' +
                    '}';
        }
    }

}
