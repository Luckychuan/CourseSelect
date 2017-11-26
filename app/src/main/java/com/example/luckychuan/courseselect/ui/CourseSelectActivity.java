package com.example.luckychuan.courseselect.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.CampusPagerAdapter;
import com.example.luckychuan.courseselect.adapter.SelectCourseRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.WeekRecycler;
import com.example.luckychuan.courseselect.util.Constant;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2017/11/20.
 */

public class CourseSelectActivity extends AppCompatActivity implements CourseSelectFragment.OnTitleChangeListener {

    private Toolbar mToolbar;
    private CourseSelectFragment mFragment1;
    private CourseSelectFragment mFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_select);

        //初始化toolbar
        mToolbar = (Toolbar) findViewById(R.id.course_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //初始化Fragments,TabLayout和ViewPager
        ArrayList<Fragment> fragments = new ArrayList<>();
        mFragment1 = new CourseSelectFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("campus", Constant.CAMPUSES[0]);
        mFragment1.setArguments(bundle1);
        mFragment1.setTitleChangeListener(this);
        fragments.add(mFragment1);

        mFragment2 = new CourseSelectFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("campus", Constant.CAMPUSES[1]);
        mFragment2.setArguments(bundle2);
        mFragment2.setTitleChangeListener(this);
        fragments.add(mFragment2);

        ViewPager viewPager = (ViewPager) findViewById(R.id.course_viewPager);
        CampusPagerAdapter adapter = new CampusPagerAdapter(getSupportFragmentManager(), fragments, Constant.CAMPUSES);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.campus_tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    /**
     * 初始化SearchView和Menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_select_activity, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        //通过MenuItem得到SearchView
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setIconifiedByDefault(true);
        searchView.setIconified(true);
        searchView.setQueryHint("输入课类、教师名");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mFragment1.filterByString(newText);
                mFragment2.filterByString(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_level:
                String[] levels = new String[Constant.LEVELS.length];
                levels[0] = "显示全部";
                for (int i = 1; i < Constant.LEVELS.length; i++) {
                    levels[i] = Constant.LEVELS[i] + "";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setSingleChoiceItems(levels, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mFragment1.filterByLevel(i);
                        mFragment2.filterByLevel(i);
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshData() {
        Toast.makeText(this, "刷新数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

}
