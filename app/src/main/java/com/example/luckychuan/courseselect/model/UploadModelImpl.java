package com.example.luckychuan.courseselect.model;

import android.util.Log;

import com.example.luckychuan.courseselect.bean.BaseBean;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2018/2/7.
 */

public class UploadModelImpl implements UploadModel {
    @Override
    public void uploadNotification(String userKey, String courseId, String title, String content, final Callback<BaseBean<Integer>> callback) {
        CustomRetrofit.getService()
                .uploadNotification(userKey, courseId, title, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean<Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean<Integer> bean) {
                        callback.onNext(bean);
                    }
                });
    }

    @Override
    public void uploadDebate(String userKey, String courseId, String content, final Callback<BaseBean<Integer>> callback) {
        CustomRetrofit.getService()
                .uploadDebate(userKey, courseId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean<Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean<Integer> bean) {
                        callback.onNext(bean);
                    }
                });
    }

    @Override
    public void uploadNotificationReply(String userKey, int replyId, String content, final Callback<BaseBean<Integer>> callback) {
        CustomRetrofit.getService()
                .uploadNotificationReply(userKey, replyId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean<Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean<Integer> bean) {
                        callback.onNext(bean);
                    }
                });
    }

    @Override
    public void uploadDebateReply(String userKey, int replyId, String content, final Callback<BaseBean<Integer>> callback) {
        CustomRetrofit.getService()
                .uploadDebateReply(userKey, replyId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean<Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean<Integer> bean) {
                        callback.onNext(bean);
                    }
                });
    }

    @Override
    public void uploadAttendanceCheck(String userKey, String courseId, RequestBody requestBody, String date, final Callback<BaseBean<Boolean>> callback) {
        CustomRetrofit.getService()
                .uploadAttendanceCheck(userKey, courseId, requestBody, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean<Boolean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("rx_java", "onError: " + e.toString());
                        callback.onError(e.toString());
                    }

                    @Override
                    public void onNext(BaseBean<Boolean> bean) {
                        callback.onNext(bean);
                    }
                });
    }
}
