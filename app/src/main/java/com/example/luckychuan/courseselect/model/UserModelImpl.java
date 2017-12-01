package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.StudentJson;

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
                        callback.onFail(e.toString());
                    }

                    @Override
                    public void onNext(StudentJson studentJson) {
                        callback.onNext(studentJson);
                    }
                });
    }
}