package com.example.luckychuan.courseselect;

import android.app.Notification;
import android.app.NotificationManager;
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
    private static final String TAG = "SelectService";
    private static final int NOTIFICATION_TASK = 100;
    private SelectTask mSelectTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null && mSelectTask == null){
            int intervalTime = intent.getIntExtra("intervalTime",30);
            int[] ids = intent.getIntArrayExtra("ids");
            mSelectTask = new SelectTask(intervalTime,ids);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        mSelectTask.stop();
        super.onDestroy();
    }

    public class MyBind extends Binder {
        public void bindView(SelectTaskView view){
            mSelectTask.bindView(view);
        }

        public void unbindView(){
            mSelectTask.unbindView();
        }

        public void startTask(){
            if(!mSelectTask.isStart()){
                mSelectTask.start();
                showNotification("任务开始");
            }
        }

        public void stopTask(){
            if(mSelectTask.isStart()){
                mSelectTask.stop();
                //// TODO: 2017/12/19  showNotification
            }
        }

        public boolean isTaskRunning(){
            if(mSelectTask == null){
                return false;
            }
            return mSelectTask.isStart();
        }
    }

    private void showNotification(String content) {
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("自动选课中");
        builder.setContentText(content);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_FOREGROUND_SERVICE;
        startForeground(NOTIFICATION_TASK,notification);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_TASK,notification);
    }

}
