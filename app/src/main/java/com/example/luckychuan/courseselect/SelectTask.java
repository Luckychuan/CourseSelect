package com.example.luckychuan.courseselect;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/12/17.
 */

public class SelectTask {

    private int mIntervalTime;
    private int[] mIds;
    private int mPosition;
    private static final String TAG = "SelectTask";

    //模拟
    private int count = 0;


    public SelectTask(int intervalTime, int[] ids) {
        this.mIntervalTime = intervalTime;
        this.mIds = ids;
    }

    public void start() {
        mPosition = 0;
        request(mIds[mPosition]);
    }

    private void countDown(final int id) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(mIntervalTime + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        SelectTask.this.request(id);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "倒计时：" + (mIntervalTime - aLong));
                    }
                });
    }


    //模拟

    private void request(int id) {
        Log.d(TAG, "开始请求 课程：" + id);
        count++;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (count < 3) {
            onError();
            return;
        }

        if (mPosition == 1 && count == 3) {
            onSuccess();
            return;
        }

        count = 0;
        onFail();


    }

    private void onError() {
        Log.d(TAG, "服务器error");
        countDown(mIds[mPosition]);
    }

    private void onFail() {
        Log.d(TAG, "课程:" + mIds[mPosition] + "已被选满");
        mPosition++;

        //任务结束
        if (mPosition == mIds.length) {
            Log.d(TAG, "任务结束，您没有选上课程");
            return;
        }

        //选下一门课程
        Log.d(TAG, "即将开始选" + mIds[mPosition]);
        countDown(mIds[mPosition]);
    }

    private void onSuccess() {
        Log.d(TAG, "任务结束：恭喜您选上了课程：" + mIds[mPosition]);
    }


}
