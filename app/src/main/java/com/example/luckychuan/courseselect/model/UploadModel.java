package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.BaseBean;

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



}
