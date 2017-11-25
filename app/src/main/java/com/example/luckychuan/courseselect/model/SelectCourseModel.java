package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.CourseJson;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public interface SelectCourseModel {

    void requestData(int level, String campus, Callback<CourseJson[]> callback);

}
