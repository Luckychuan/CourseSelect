package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.BaseBean;

import okhttp3.RequestBody;

/**
 * Created by Luckychuan on 2018/2/7.
 */

public interface UploadModel {

    void uploadNotification(String userKey, String courseId, String title, String content,
                            Callback<BaseBean<Integer>> callback);

    void uploadDebate(String userKey, String courseId, String content,
                      Callback<BaseBean<Integer>> callback);

    void uploadNotificationReply(String userKey, int replyId, String content,
                                 Callback<BaseBean<Integer>> callback);

    void uploadDebateReply(String userKey, int replyId, String content,
                           Callback<BaseBean<Integer>> callback);

    void uploadAttendanceCheck(String userKey, String courseId, RequestBody requestBody ,String date ,Callback<BaseBean<Boolean>> callback);


}
