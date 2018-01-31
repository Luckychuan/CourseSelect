package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class NewsContent {

    @SerializedName("ID")
    private int id;
    @SerializedName("A_TITLE")
    private String title;
    @SerializedName("A_CONTENT")
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewsContent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
