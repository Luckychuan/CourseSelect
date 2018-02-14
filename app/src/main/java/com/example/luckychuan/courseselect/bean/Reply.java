package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luckychuan on 2018/2/13.
 */

public class Reply {

    @SerializedName("ID")
    private int id;
    @SerializedName("USERID")
    private String authorId;
    @SerializedName("userName")
    private String authorName;
    @SerializedName("CONTENT")
    private String content;
    private String time;
    @SerializedName("preUserId")
    private String replyToId;
    @SerializedName("preUserName")
    private String replyToName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(String replyToId) {
        this.replyToId = replyToId;
    }

    public String getReplyToName() {
        return replyToName;
    }

    public void setReplyToName(String replyToName) {
        this.replyToName = replyToName;
    }
}
