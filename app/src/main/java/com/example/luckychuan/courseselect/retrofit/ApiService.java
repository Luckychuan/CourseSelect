package com.example.luckychuan.courseselect.retrofit;

import com.example.luckychuan.courseselect.bean.CourseInfoJson;
import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.bean.SelectCourseJson;
import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public interface ApiService {

    /**
     * 获取可选课程列表
     *
     * @param level
     * @param userKey
     * @param campus
     * @return
     */

