package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.TabPagerAdapter;
import com.example.luckychuan.courseselect.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class CourseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //初始化toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.course_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(Constant.FUNCTIONS[0]);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ArrayList<Fragment> list = new ArrayList<>();
        Fragment fragment3 =  new CourseChatFragment();
        Fragment fragment1=  new CourseInfoFragment();
        Fragment fragment2 =  new CourseNotificationFragment();
        Fragment fragment4 =  new CourseRateFragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);

        ViewPager viewPager = (ViewPager) findViewById(R.id.function_viewPager);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(),list, Constant.FUNCTIONS);
        viewPager.setAdapter(adapter);

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


}
