package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.AttendanceCheck;
import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Date;

/**
 * Created by Luckychuan on 2018/3/10.
 */

public interface AttendanceCheckModel {

    void getAttendanceCheckDates( String userKey, String courseId,Callback<BaseBeanArray<Date>> callback);
    void getAttendanceChecks(String userKey,  String courseId,String date, Callback<BaseBeanArray<AttendanceCheck>> callback);

}
