package com.example.luckychuan.courseselect.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Luckychuan on 2018/2/8.
 */

public class Message implements Serializable {

    @SerializedName("ID")
    private int id  ;
    @SerializedName("USERID")
    private String account ;
    @SerializedName("TITLE")
    private String title ;
    @SerializedName("CONTENT")
    private String content ;
    @SerializedName("count")
    private int commentSize ;
    @SerializedName("name")
    private String userName ;
    private String  time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitile(String titile) {
        this.title = titile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
