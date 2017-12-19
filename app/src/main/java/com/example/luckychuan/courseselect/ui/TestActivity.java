package com.example.luckychuan.courseselect.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.SelectService;
import com.example.luckychuan.courseselect.SelectTask;
import com.example.luckychuan.courseselect.bean.CourseInfoJson;
import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.bean.SelectCourseJson;
import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;
import com.example.luckychuan.courseselect.view.SelectTaskView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/12/10.
 */

public class TestActivity extends Activity implements SelectTaskView {
    private static final String TAG = "TestActivity";
    private SelectService.MyBind mMyBind;
    private MyConn mMyConn;

    private Button mTaskButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //启动service
        mMyConn = new MyConn();
        final Intent intent =new Intent(TestActivity.this, SelectService.class);
        intent.putExtra("intervalTime",2);
        intent.putExtra("ids",new int[]{1,2,3});
        startService(intent);
        bindService(intent,mMyConn,BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        //如果任务已经完成
        if(!mMyBind.isTaskRunning()){
            unbindService(mMyConn);
            stopService(new Intent(TestActivity.this,SelectService.class));
        }else{
            mMyBind.unbindView();
            unbindService(mMyConn);
        }
        super.onDestroy();
    }

    @Override
    public void showCountDown(int countDown) {
        Log.d(TAG, "还有"+countDown+"秒开始下一次选入");
    }

    @Override
    public void showRequestMsg(String requestMsg) {
        Log.d(TAG, requestMsg);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        Log.d(TAG, errorMsg);
    }

    @Override
    public void onFail(String failMsg) {
        Log.d(TAG, failMsg);
        mTaskButton.setText("开始");
    }

    @Override
    public void onSuccess(String successMsg) {
        Log.d(TAG, successMsg);
        mTaskButton.setText("开始");
    }

    @Override
    public void showLog(String log) {

    }

    @Override
    public void showTaskEndMsg(String endMsg) {
        Log.d(TAG, endMsg);
    }

    @Override
    public void showNextMsg(String nextMsg) {
        Log.d(TAG, nextMsg);
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("bindLog","bind");
            mMyBind= (SelectService.MyBind) service;
            mMyBind.bindView(TestActivity.this);

            //初始化button
            mTaskButton = (Button)findViewById(R.id.start_task);
            if(mMyBind.isTaskRunning()){
                mTaskButton.setText("停止");
            }else{
                mTaskButton.setText("开始");
            }
            mTaskButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!mMyBind.isTaskRunning()){
                        Toast.makeText(TestActivity.this, "开始任务", Toast.LENGTH_SHORT).show();
                        mMyBind.startTask();
                        mTaskButton.setText("停止");
                    }else{
                        Toast.makeText(TestActivity.this, "停止任务", Toast.LENGTH_SHORT).show();
                        mMyBind.stopTask();
                        mTaskButton.setText("开始");
                    }
                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("bindLog","unbind");
        }
    }


}
