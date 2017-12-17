package com.example.luckychuan.courseselect;

import android.util.Log;

import com.example.luckychuan.courseselect.view.SelectTaskView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/12/17.
 */

public class SelectTask {

    private SelectTaskView mView;
    private static final String TAG = "SelectTask";

    private boolean isStart;

    private int mIntervalTime;
    private int[] mIds;
    private int mPosition;

    private Subscriber<Long> mSubscriber;

    //模拟
    private int count = 0;



    public SelectTask(int intervalTime, int[] ids) {
        isStart = false;
        this.mIntervalTime = intervalTime;
        this.mIds = ids;
    }

    public void bindView(SelectTaskView view) {
        mView = view;
    }

    public void unbindView() {
        mView = null;
    }

    public void start() {
        isStart = true;
        mPosition = 0;
        request(mIds[mPosition]);
    }

    public boolean isStart(){
        return isStart;
    }

    public void stop() {
        isStart = false;
        if (mSubscriber != null) {
            mSubscriber.unsubscribe();
            //// TODO: 2017/12/17  停止更新UI
        }
    }

    private void countDown(final int id) {
        mSubscriber = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                SelectTask.this.request(id);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                if (mView != null) {
                    mView.showCountDown((int) (mIntervalTime - aLong));
                }
            }
        };

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(mIntervalTime + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }


    //模拟

    private void request(int id) {
        if (mView != null) {
            mView.showRequestMsg("正在请求课程："+id);
        }
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

//        if (mPosition == 1 && count == 3) {
//            onSuccess();
//            return;
//        }

        count = 0;
        onFail();


    }

    private void onError() {
        if (mView != null) {
            mView.showErrorMsg("无法连接至服务器");
        }
        countDown(mIds[mPosition]);
    }

    private void onFail() {
        if (mView != null) {
            mView.showFailMsg("课程:" + mIds[mPosition] + "已被选满");
        }
        mPosition++;

        //任务结束
        if (mPosition == mIds.length) {
            if (mView != null) {
                mView.showTaskEndMsg("任务结束，您没有选上课程");
            }
            return;
        }

        //选下一门课程
        if (mView != null) {
            mView.showNextMsg("即将开始选" + mIds[mPosition]);
        }
        countDown(mIds[mPosition]);
    }

    private void onSuccess() {
        if (mView != null) {
            mView.onSuccess("任务结束：恭喜您选上了课程：" + mIds[mPosition]);
        }
    }


}
