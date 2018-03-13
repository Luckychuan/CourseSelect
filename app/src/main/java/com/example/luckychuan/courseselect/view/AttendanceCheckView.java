package com.example.luckychuan.courseselect.view;

import com.example.luckychuan.courseselect.bean.AttendanceCheck;
import com.example.luckychuan.courseselect.bean.Date;

/**
 * Created by Luckychuan on 2018/3/10.
 */

public interface AttendanceCheckView extends BaseView {

    void onSuccess(Date[] dates);
    void onSuccess(AttendanceCheck[] checks);

    void onStudentInfoSuccess(AttendanceCheck[] checks);
}
