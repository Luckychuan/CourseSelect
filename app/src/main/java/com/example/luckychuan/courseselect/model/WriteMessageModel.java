package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.BaseBean;

/**
 * Created by Luckychuan on 2018/2/7.
 */

public interface WriteMessageModel {

    void uploadNotification(String userKey, String courseId, String title, String content,
                            Callback<BaseBean<Integer>> callback);



}
