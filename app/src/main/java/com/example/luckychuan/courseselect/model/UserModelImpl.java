package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/11/30.
 */

public class UserModelImpl implements UserModel {
    @Override
    public void requestStudent(String account, String password, final Callback<StudentJson> callback) {
        CustomRetrofit.getService()
                .getStudent(account,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StudentJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器");
                    }

                    @Override
                    public void onNext(StudentJson studentJson) {
                        callback.onNext(studentJson);
                    }
                });
    }

    @Override
    public void requestTeacher(String account, String password, final Callback<TeacherJson> callback) {
        CustomRetrofit.getService()
                .getTeacher(account,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TeacherJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器");
                    }

                    @Override
                    public void onNext(TeacherJson teacherJson) {
                        callback.onNext(teacherJson);
                    }
                });
    }
}
