package com.example.luckychuan.courseselect.view;

import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public interface LoginView extends BaseView {
    void onResponse(StudentJson student);

    void onResponse(TeacherJson teacher);

}
