package com.example.luckychuan.courseselect.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.luckychuan.courseselect.SelectTask;
import com.example.luckychuan.courseselect.bean.CourseInfoJson;
import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.bean.SelectCourseJson;
import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/12/10.
 */

public class TestActivity extends Activity {
    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new SelectTask(2,new int[]{1,2,3}).start();


    }


}
