package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.MyCourse;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public class MyCourseModelImpl implements MyCourseModel {
    @Override
    public void requestMyCourse(String userKey, final Callback<BaseBeanArray<MyCourse>> callback) {
        CustomRetrofit.getService()
                .getMyCourse(userKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBeanArray<MyCourse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器");
                    }

                    @Override
                    public void onNext(BaseBeanArray<MyCourse> json) {
                        callback.onNext(json);
                    }
                });
    }

}
