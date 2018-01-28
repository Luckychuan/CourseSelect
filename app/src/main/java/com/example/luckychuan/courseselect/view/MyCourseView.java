package com.example.luckychuan.courseselect.view;

import com.example.luckychuan.courseselect.bean.StudentMyCourse;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public interface MyCourseView extends BaseView {

    void onSuccess(StudentMyCourse.Data[] data);

}
