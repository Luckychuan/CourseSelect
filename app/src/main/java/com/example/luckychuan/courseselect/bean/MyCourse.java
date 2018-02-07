package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public class MyCourse {

    @SerializedName("KCBH")
    private String id;
    @SerializedName("KCMC")
    private String name;
    @SerializedName("RKJS")
    private String teacher;
    @SerializedName("SKZC")
    private String duration;
    @SerializedName("WEEK")
    private int week;
    @SerializedName("JC")
    private int time;
    @SerializedName("XQ")
    private String campus;


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


    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", duration='" + duration + '\'' +
                ", week=" + week +
                ", time=" + time +
                ", campus='" + campus + '\'' +
                '}';
    }
}


