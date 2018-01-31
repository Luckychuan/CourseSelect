package com.example.luckychuan.courseselect.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.luckychuan.courseselect.view.BaseView;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Luckychuan on 2018/1/23.
 */

public class BaseActivity extends AppCompatActivity implements BaseView{

    private ProgressBar mProgressBar;
    private static ArrayList<Activity> activities = new ArrayList<Activity>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }



    private static void finishAll(){

        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    @Override
    public void showProgressbar() {
        if(mProgressBar !=null){
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressbar() {
        if(mProgressBar != null){
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String failMsg) {
        Toast.makeText(this, failMsg, Toast.LENGTH_SHORT).show();
    }


    public static class JPushReceiver extends BroadcastReceiver {

        private static final String TAG = "JPushReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();

            if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));

                String title = bundle.getString(JPushInterface.EXTRA_TITLE);
                String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);

                //发来的消息事件为强制下线
                if(title != null && title.equals("强制下线")){
                    doForceOffline(context,content);
                }
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "收到了通知");
                // 在这里可以做些统计，或者做些其他工作
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.d(TAG, "用户点击打开了通知");
                // 在这里可以自己写代码去定义用户点击后的行为
                //   Intent i = new Intent(context, TestActivity.class);  //自定义打开的界面
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
            }
        }

        private void doForceOffline(Context context,String msg) {
            finishAll();
            JPushInterface.deleteAlias(context,0);
            Intent intent = new Intent(context,LoginActivity.class);
            intent.putExtra("logout",true);
            intent.putExtra("forceOfflineMsg",msg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }


    }



}
