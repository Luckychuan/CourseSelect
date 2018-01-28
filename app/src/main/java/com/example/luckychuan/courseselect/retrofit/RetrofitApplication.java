package com.example.luckychuan.courseselect.retrofit;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public class RetrofitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CustomRetrofit.getInstance().init(getApplicationContext());

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
