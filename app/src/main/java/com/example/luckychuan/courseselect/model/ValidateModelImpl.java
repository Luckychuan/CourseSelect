package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.BaseBean;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2018/1/24.
 */

public class ValidateModelImpl implements ValidateAccountModel {
    @Override
    public void requestValidate(String userKey, final Callback<BaseBean<Boolean>> callback) {
        CustomRetrofit.getService()
                .validateAccount(userKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean<Boolean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器");
                    }

                    @Override
                    public void onNext(BaseBean<Boolean> bean) {
                        callback.onNext(bean);
                    }
                });
    }
}
