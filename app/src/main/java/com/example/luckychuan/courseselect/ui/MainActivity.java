package com.example.luckychuan.courseselect.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class MainActivity extends AppCompatActivity implements MeFragment.OnLogoutListener {

    private static final String TAG = "MainActivity";
    private MyCourseFragment mMyCourseFragment;
    private NotificationFragment mNotificationFragment;
    private MeFragment mMeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("课程列表");
        final Button button = (Button) findViewById(R.id.select_course_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CourseSelectActivity.class));
            }
        });


        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_course, "课程"))
                .addItem(new BottomNavigationItem(R.drawable.ic_notification, "通知"))
                .addItem(new BottomNavigationItem(R.drawable.ic_me, "我的"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {//这里也可以使用SimpleOnTabSelectedListener
            @Override
            public void onTabSelected(int position) {//未选中 -> 选中
                switch (position) {
                    case 0:
                        title.setText("课程列表");
                        showMyCourseFragment();
                        button.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        title.setText("通知公告");
                        showNotificationFragment();
                        button.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        title.setText("个人信息");
                        button.setVisibility(View.INVISIBLE);
                        showMeFragment();
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {//选中 -> 未选中
            }

            @Override
            public void onTabReselected(int position) {//选中 -> 选中

            }
        });
        bottomNavigationBar.selectTab(0);

    }

    private void showNotificationFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
        if (mMyCourseFragment != null) {
            transaction.hide(mMyCourseFragment);
        }

        if (mNotificationFragment == null) {
            mNotificationFragment = new NotificationFragment();
            transaction.add(R.id.main_fragment_layout, mNotificationFragment);
        } else {
            transaction.show(mNotificationFragment);
        }

        transaction.commit();
    }

    private void showMeFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mMyCourseFragment != null) {
            transaction.hide(mMyCourseFragment);
        }
        if (mNotificationFragment != null) {
            transaction.hide(mNotificationFragment);
        }

        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
            ((MeFragment)mMeFragment).setOnLogoutListener(this);
            transaction.add(R.id.main_fragment_layout, mMeFragment);
        } else {
            transaction.show(mMeFragment);
        }

        transaction.commit();
    }

    private void showMyCourseFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mMeFragment != null) {
            transaction.hide(mMeFragment);
        }
        if (mNotificationFragment != null) {
            transaction.hide(mNotificationFragment);
        }

        if (mMyCourseFragment == null) {
            mMyCourseFragment = new MyCourseFragment();
            transaction.add(R.id.main_fragment_layout, mMyCourseFragment);
        } else {
            transaction.show(mMyCourseFragment);
        }

        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        //返回桌面
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }


    @Override
    public void onLogout() {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("logout",true);
        startActivity(intent);
        finish();
    }
}
