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
    private boolean isServiceStart;
    private SelectService.MyBind mMyBind;
    private MyConn mMyConn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mMyConn = new MyConn();
        final Intent intent =new Intent(TestActivity.this, SelectService.class);
        intent.putExtra("intervalTime",2);
        intent.putExtra("ids",new int[]{1,2,3});

        isServiceStart = false;

        final Button startButton = (Button)findViewById(R.id.start_task);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isServiceStart){
                    isServiceStart = true;
                    Toast.makeText(TestActivity.this, "开始任务", Toast.LENGTH_SHORT).show();
                    startService(intent);
                    bindService(intent,mMyConn,BIND_AUTO_CREATE);
                    startButton.setText("停止");
                    return;
                }

                //如果已经启动
                isServiceStart = false;
                Toast.makeText(TestActivity.this, "停止任务", Toast.LENGTH_SHORT).show();
                unbindService(mMyConn);
                stopService(intent);
                startButton.setText("开始");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyBind.unbindView();
        unbindService(mMyConn);
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
    public void showFailMsg(String failMsg) {
        Log.d(TAG, failMsg);
    }

    @Override
    public void onSuccess(String successMsg) {
        Log.d(TAG, successMsg);
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
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("bindLog","unbind");
        }
    }


}
