package com.example.luckychuan.courseselect.view;

import com.example.luckychuan.courseselect.bean.CourseInfoJson;
import com.example.luckychuan.courseselect.bean.CourseJson;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public interface SelectCourseView extends BaseView {


    void addData(CourseJson[] courseJson);

    void loadSelectCourseUI();

    void showCourseInfo(CourseInfoJson courseInfoJson);

}
