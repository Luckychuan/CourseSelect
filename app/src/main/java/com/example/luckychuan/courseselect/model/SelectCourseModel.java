package com.example.luckychuan.courseselect.model;


import com.example.luckychuan.courseselect.bean.CourseInfoJson;
import com.example.luckychuan.courseselect.bean.CourseJson;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public interface SelectCourseModel {

    void requestData(int[] levels,String userKey ,String campus, Callback<CourseJson[]> callback);

    void requestCourseInfo(String userKey ,String id, Callback<CourseInfoJson> callback);

}
