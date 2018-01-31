package com.example.luckychuan.courseselect.model;

import android.util.Log;

import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.News;
import com.example.luckychuan.courseselect.bean.NewsContent;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class NewModelImpl implements NewsModel {
    @Override
    public void requestNewsTitle(String userKey,int page, final Callback<BaseBeanArray<News>> callback) {
        CustomRetrofit.getService()
                .getNewsTitle(page,userKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBeanArray<News>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("无法连接至服务器");
                        Log.d("rx_java", "onError: "+e.toString());
                    }

                    @Override
                    public void onNext(BaseBeanArray<News> newsBaseBeanArray) {
                        callback.onNext(newsBaseBeanArray);
                    }
                });
    }

    @Override
    public void requestNewsContent(String userKey, int id, final Callback<BaseBeanArray<NewsContent>> callback) {
        CustomRetrofit.getService()
                .getNewsContent(id,userKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBeanArray<NewsContent>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("rx_java", "onError: "+e.toString());
                        callback.onError("无法连接至服务器");

                    }

                    @Override
                    public void onNext(BaseBeanArray<NewsContent> newsContentBaseBean) {
                        callback.onNext(newsContentBaseBean);

                    }
                });
    }
}
