package com.example.luckychuan.courseselect.model;


import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Message;

import retrofit2.http.Query;

/**
 * Created by Luckychuan on 2018/2/8.
 */

public interface MessageModel {

    void getNotification(String userKey, String courseId, Callback<BaseBeanArray<Message>> callback);

    void getDebate(String userKey, String courseId, int page,Callback<BaseBeanArray<Message>> callback);


}
