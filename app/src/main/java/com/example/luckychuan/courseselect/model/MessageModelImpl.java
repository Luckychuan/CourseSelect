package com.example.luckychuan.courseselect.model;


import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Message;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2018/2/8.
 */

public class MessageModelImpl implements MessageModel {

    @Override
    public void getNotification(String userKey, String courseId, final Callback<BaseBeanArray<Message>> callback) {
        CustomRetrofit.getService()
                .getNotification(userKey,courseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBeanArray<Message>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器");
                    }

                    @Override
                    public void onNext(BaseBeanArray<Message> bean) {
                        callback.onNext(bean);
                    }
                });
    }

    @Override
    public void getDebate(String userKey, String courseId, int page, final Callback<BaseBeanArray<Message>> callback) {
        CustomRetrofit.getService()
                .getDebate(userKey, courseId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBeanArray<Message>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器");
                    }

                    @Override
                    public void onNext(BaseBeanArray<Message> bean) {
                        callback.onNext(bean);
                    }
                });
    }
}
