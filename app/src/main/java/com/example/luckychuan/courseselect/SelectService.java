package com.example.luckychuan.courseselect;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.luckychuan.courseselect.view.SelectTaskView;

/**
 * Created by Luckychuan on 2017/12/17.
 */

public class SelectService extends Service {

    private SelectTask mSelectTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            int intervalTime = intent.getIntExtra("intervalTime",30);
            int[] ids = intent.getIntArrayExtra("ids");
            mSelectTask = new SelectTask(intervalTime,ids);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mSelectTask.stop();
        super.onDestroy();
    }

    public class MyBind extends Binder {
        public void bindView(SelectTaskView view){
            mSelectTask.bindView(view);
            if(!mSelectTask.isStart()){
                mSelectTask.start();
                //// TODO: 2017/12/17 通知
            }
        }

        public void unbindView(){
            mSelectTask.unbindView();
        }

    }

}
