package com.example.luckychuan.courseselect.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.TabPagerAdapter;
import com.example.luckychuan.courseselect.util.Constant;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class CourseActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {


    private String mCourseId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //初始化toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.course_toolbar);
        toolbar.setTitle("课程信息");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if(LoginActivity.getUser() == LoginActivity.TEACHER){
            toolbar.inflateMenu(R.menu.menu_my_course);
            toolbar.setOnMenuItemClickListener(this);
        }

        mCourseId = getIntent().getStringExtra("course_id");


        ArrayList<Fragment> list = new ArrayList<>();

        //课程列表
        Fragment fragment1=  new CourseInfoFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("course_id",mCourseId);
        fragment1.setArguments(bundle1);

        //通知
        Fragment fragment2 =  new MessageFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("course_id",mCourseId);
        bundle2.putInt("function",MessageFragment.TYPE_NOTIFICATION);
        fragment2.setArguments(bundle2);

        //讨论
        Fragment fragment3 =  new MessageFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("function",MessageFragment.TYPE_DEBATE);
        bundle3.putString("course_id",mCourseId);
        fragment3.setArguments(bundle3);

        Fragment fragment4 =  new CourseRateFragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);

        ViewPager viewPager = (ViewPager) findViewById(R.id.function_viewPager);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(),list, Constant.FUNCTIONS);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.function_tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < Constant.FUNCTION_DRAWABLE_IDS.length; i++) {
            tabLayout.getTabAt(i).setIcon(getDrawable(Constant.FUNCTION_DRAWABLE_IDS[i])).setText("");
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(Constant.FUNCTIONS[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.attendance_check:
                Intent intent = new Intent(this,CheckActivity.class);
                intent.putExtra("course_id",mCourseId);
                startActivity(intent);
                break;
        }

        return true;
    }
}
