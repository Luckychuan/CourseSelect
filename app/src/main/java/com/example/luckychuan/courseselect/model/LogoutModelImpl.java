package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.LogoutJson;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/12/12.
 */

public class LogoutModelImpl implements LogoutModel {
    @Override
    public void requestLogout(String userKey, final Callback<LogoutJson> callback) {
        CustomRetrofit.getService()
                .getLogout(userKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LogoutJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LogoutJson json) {
                    }
                });
    }
}
