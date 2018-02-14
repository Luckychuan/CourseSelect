package com.example.luckychuan.courseselect.model;

import android.util.Log;

import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Reply;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2018/2/14.
 */

public class ReplyModelImpl implements ReplyModel {
    @Override
    public void getReplies(String userKey, int replyId, final Callback<BaseBeanArray<Reply>> callback) {
        CustomRetrofit.getService()
                .getReplies(userKey,replyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBeanArray<Reply>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器"+e.toString());
                        Log.d("rx_java", "onError: "+e.toString());
                    }

                    @Override
                    public void onNext(BaseBeanArray<Reply> bean) {
                        callback.onNext(bean);
                    }
                });
    }
}
