package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.StudentMyCourse;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public interface MyCourseModel {

    void requestStudentMyCourse(String userKey,Callback<StudentMyCourse> callback);

}
