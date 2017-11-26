package com.example.luckychuan.courseselect.bean;

/**
 * Created by Luckychuan on 2017/11/25.
 */

public class WeekRecycler {

    private int week;
    private int size;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "WeekRecycler{" +
                "week=" + week +
                ", size=" + size +
                '}';
    }
}
