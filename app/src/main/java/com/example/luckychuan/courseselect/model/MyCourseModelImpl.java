package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.StudentMyCourse;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public class MyCourseModelImpl implements MyCourseModel {
    @Override
    public void requestStuedentMyCourse(String userKey, final Callback<StudentMyCourse> callback) {
        CustomRetrofit.getService()
                .getMyCourse(userKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StudentMyCourse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器");
                    }

                    @Override
                    public void onNext(StudentMyCourse json) {
                        callback.onNext(json);
                    }
                });
    }
}
