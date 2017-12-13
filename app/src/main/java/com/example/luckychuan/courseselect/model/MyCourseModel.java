package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.MyCourseJson;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public interface MyCourseModel {

    void requestMyCourse(String userKey,Callback<MyCourseJson> callback);

}
