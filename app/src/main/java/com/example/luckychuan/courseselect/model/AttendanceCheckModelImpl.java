package com.example.luckychuan.courseselect.model;

import android.util.Log;

import com.example.luckychuan.courseselect.bean.AttendanceCheck;
import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Date;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2018/3/10.
 */

public class AttendanceCheckModelImpl implements AttendanceCheckModel {


    @Override
    public void getAttendanceCheckDates(String userKey, String courseId, final Callback<BaseBeanArray<Date>> callback) {
        CustomRetrofit.getService()
                .getAttendanceCheckDates(userKey, courseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBeanArray<Date>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器" + e.toString());
                        Log.d("rx_java", "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(BaseBeanArray<Date> bean) {
                        callback.onNext(bean);
                    }
                });
    }

    @Override
    public void getAttendanceChecks(String userKey, String courseId, String date, final Callback<BaseBeanArray<AttendanceCheck>> callback) {
        CustomRetrofit.getService()
                .getAttendanceChecks(userKey, courseId,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBeanArray<AttendanceCheck>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器" + e.toString());
                        Log.d("rx_java", "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(BaseBeanArray<AttendanceCheck> bean) {
                        callback.onNext(bean);
                    }
                });
    }
}
