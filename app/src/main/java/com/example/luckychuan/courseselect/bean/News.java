package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class News {

    @SerializedName("ID")
    private String id;
    @SerializedName("Title")
    private String title;
    private String time;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
