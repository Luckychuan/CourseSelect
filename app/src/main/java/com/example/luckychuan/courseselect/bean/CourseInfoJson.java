package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 可选课程的详细信息
 */

public class CourseInfoJson {
    @SerializedName("success")
    private boolean isSuccess;
    @SerializedName("msgcode")
    private int msgCode;
    private String error;
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "CourseInfoJson{" +
                "isSuccess=" + isSuccess +
                ", msgCode=" + msgCode +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }

    public static class Data {
        @SerializedName("keChengBianHao")
        private String id;
        @SerializedName("keChengMingCheng")
        private String name;
        @SerializedName("xueFen")
        private int credit;
        @SerializedName("yunDongXiangMu")
        private String sportName;
        @SerializedName("renKeJiaoShi")
        private String teacher;
        private String zhiGongHao;
        @SerializedName("shangKeZhouCi")
        private String duration;
        @SerializedName("xingQi")
        private int week;
        @SerializedName("jieCi")
        private int time;
        @SerializedName("xiaoQu")
        private String campus;
        @SerializedName("rongLiang")
        private int size;
        @SerializedName("jiBie")
        private int level;
        @SerializedName("keChengLeiXing")
        private String type;
        @SerializedName("xingBie")
        private String sex;
        @SerializedName("xueNian")
        private int year;
        @SerializedName("xueQi")
        private int term;

        public String getZhiGongHao() {
            return zhiGongHao;
        }

        public void setZhiGongHao(String zhiGongHao) {
            this.zhiGongHao = zhiGongHao;
        }

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

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public String getSportName() {
            return sportName;
        }

        public void setSportName(String sportName) {
            this.sportName = sportName;
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
            this.duration = duration + "周";
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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getTerm() {
            return term;
        }

        public void setTerm(int term) {
            this.term = term;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", credit=" + credit +
                    ", sportName='" + sportName + '\'' +
                    ", teacher='" + teacher + '\'' +
                    ", duration='" + duration + '\'' +
                    ", week=" + week +
                    ", time=" + time +
                    ", campus='" + campus + '\'' +
                    ", size=" + size +
                    ", level=" + level +
                    ", type='" + type + '\'' +
                    ", sex='" + sex + '\'' +
                    ", year=" + year +
                    ", term=" + term +
                    '}';
        }
    }


}
