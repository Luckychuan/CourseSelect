package com.example.luckychuan.courseselect.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.SelectCourseRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.bean.CourseRecycler;
import com.example.luckychuan.courseselect.bean.WeekRecycler;
import com.example.luckychuan.courseselect.presenter.SelectCoursePresenter;
import com.example.luckychuan.courseselect.test.TestJsonData;
import com.example.luckychuan.courseselect.util.Constant;
import com.example.luckychuan.courseselect.util.FormatUtil;
import com.example.luckychuan.courseselect.view.SelectCourseView;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Luckychuan on 2017/11/20.
 */

public class CourseSelectFragment extends Fragment implements SelectCourseView {

    private static final String TAG = "CourseSelectFragment";

    private SwipeRefreshLayout mRefreshLayout;

    private String mCampus;
    private SelectCoursePresenter mPresenter;

    //http返回来的数据集
    private ArrayList<CourseJson> mJsonCourses;

    //RecyclerView要用的的List
    private ArrayList<SelectCourseRecyclerAdapter.RecyclerItem> mRecyclerItems;
    //筛选之后的数据集
    private ArrayList<SelectCourseRecyclerAdapter.RecyclerItem> mFilterItems;

    private SelectCourseRecyclerAdapter mAdapter;

    private OnTitleChangeListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_select, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCampus = getArguments().getString("campus");
        mPresenter = new SelectCoursePresenter(this);
        mPresenter.attach(this);

        mJsonCourses = new ArrayList<>();
        mRecyclerItems = new ArrayList<>();
        mFilterItems = new ArrayList<>();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_course);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SelectCourseRecyclerAdapter(mFilterItems);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //获得RecyclerView当前可见的item的日期，给ToolBar设置日期
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = manager.findFirstVisibleItemPosition();
                String week = mAdapter.getWeekString(position);
                if (week == null) {
                    mListener.changeToolbarTitle(getContext().getString(R.string.selectCourseActivity_title));
                } else {
                    mListener.changeToolbarTitle(week);
                }
            }
        });

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(Color.parseColor("#03A9F4"));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestSelectCourse();
            }
        });

        //设置悬浮按钮
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.float_button);
        button.attachToRecyclerView(recyclerView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setSingleChoiceItems(Constant.WEEK, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //找到当前星期在list中的position
                        int position = 0;
                        for (int j = 0; j < mRecyclerItems.size(); j++) {
                            SelectCourseRecyclerAdapter.RecyclerItem item = mRecyclerItems.get(j);
                            if (item.type == SelectCourseRecyclerAdapter.WEEK) {
                                if (((WeekRecycler) item.bean).getWeek() == i + 1) {
                                    position = j;
                                }
                            }
                        }
                        //定位到当前位置
                        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        manager.scrollToPositionWithOffset(position, 0);
                        manager.setStackFromEnd(true);

                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });


        requestSelectCourse();


    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void toastErrorMsg(String errorMsg) {

//        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "toastErrorMsg: " + errorMsg);

//        for (CourseJson c : TestJsonData.getTestCourseData()) {
////            Log.d(TAG, "e: "+ c.toString());
//            mOriginCourse.add(c);
//        }
    }

    @Override
    public void requestSelectCourse() {
        mFilterItems.clear();
        mJsonCourses.clear();
        mRecyclerItems.clear();
        if (mCampus.equals("长安校区")) {
            mPresenter.requestData(new int[]{1, 3}, mCampus);
        } else if (mCampus.equals("太白校区")) {
            mPresenter.requestData(new int[]{2, 3}, mCampus);
        }

//        //// TODO: 2017/11/26
//        mPresenter.requestData(Constant.LEVELS, mCampus);
    }

    @Override
    public void addData(CourseJson[] courseJson) {
        for (CourseJson c : courseJson) {
            mJsonCourses.add(c);
        }
    }

    @Override
    public void loadSelectCourseUI() {
        mRefreshLayout.setRefreshing(false);

        //按星期排序
        Collections.sort(mJsonCourses, new Comparator<CourseJson>() {
            @Override
            public int compare(CourseJson courseJson, CourseJson t1) {
                if (courseJson.getWeek() > t1.getWeek()) {
                    return 1;
                } else if (courseJson.getWeek() < t1.getWeek()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        //打印结果
//        for(CourseJson cj: mJsonCourses){
//            Log.d("mJsonCourses", cj.toString());
//        }

        //为RecyclerView添加item
        WeekRecycler[] weeks = new WeekRecycler[6];
        for (CourseJson cj : mJsonCourses) {
            //添加Week
            int w = cj.getWeek();
            int i = w - 1;
            if (weeks[i] == null) {
                weeks[i] = new WeekRecycler();
                weeks[i].setWeek(w);
                weeks[i].setSize(0);
                SelectCourseRecyclerAdapter.RecyclerItem<WeekRecycler> weekItem =
                        new SelectCourseRecyclerAdapter.RecyclerItem<>(SelectCourseRecyclerAdapter.WEEK, weeks[i]);
                mRecyclerItems.add(weekItem);
            }

            //添加Course
            String time = "节次" + cj.getSection();
            int level = cj.getLevel();
            for (CourseJson.Course course : cj.getCourses()) {
                String name = course.getName();
                String teacher = course.getTeacher();
                String studentLeft = course.getStudentLeft();
                String teacherId = course.getTeacherId();
                String id = course.getId();
                CourseRecycler cr = new CourseRecycler(name, time, teacher, level, studentLeft, teacherId, id);
                SelectCourseRecyclerAdapter.RecyclerItem<CourseRecycler> item =
                        new SelectCourseRecyclerAdapter.RecyclerItem<>(SelectCourseRecyclerAdapter.COURSE, cr);
                mRecyclerItems.add(item);

                weeks[i].setSize(weeks[i].getSize() + 1);
            }
            mFilterItems.addAll(mRecyclerItems);
            mAdapter.notifyDataSetChanged();

//        //打印结果
//        for (SelectCourseRecyclerAdapter.RecyclerItem ri : mRecyclerItems) {
//            if (ri.type == SelectCourseRecyclerAdapter.WEEK) {
//                Log.d("mRecyclerItems", ((WeekRecycler) ri.bean).toString());
//            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }



    /**
     * 按等级筛选
     *
     * @param level
     */
    public void filterByLevel(int level) {
        mFilterItems.clear();

        //显示全部，不筛选
        if (level == 0) {
            mFilterItems.addAll(mRecyclerItems);
            mAdapter.notifyDataSetChanged();
            return;
        }

        WeekRecycler weekRecycler = null;
        for (SelectCourseRecyclerAdapter.RecyclerItem item : mRecyclerItems) {
            if (item.type == SelectCourseRecyclerAdapter.WEEK) {
                weekRecycler = new WeekRecycler();
                weekRecycler.setWeek(((WeekRecycler) item.bean).getWeek());
                weekRecycler.setSize(0);
                SelectCourseRecyclerAdapter.RecyclerItem weekItem =
                        new SelectCourseRecyclerAdapter.RecyclerItem(SelectCourseRecyclerAdapter.WEEK,weekRecycler);
                mFilterItems.add(weekItem);
            } else {
                if (((CourseRecycler) item.bean).getLevel() == level) {
                    if (weekRecycler != null) {
                        weekRecycler.setSize(weekRecycler.getSize() + 1);
                    }
                    mFilterItems.add(item);
                }
            }
        }
        mAdapter.notifyDataSetChanged();

    }


    /**
     * 搜索筛选
     * @param
     */
    public void filterByString(String string) {
        mFilterItems.clear();

        if (string.equals("")) {
            mFilterItems.addAll(mRecyclerItems);
            mAdapter.notifyDataSetChanged();
            return;
        }

        WeekRecycler weekRecycler = null;
        for (SelectCourseRecyclerAdapter.RecyclerItem item : mRecyclerItems) {
            if (item.type == SelectCourseRecyclerAdapter.WEEK) {
                weekRecycler = new WeekRecycler();
                weekRecycler.setWeek(((WeekRecycler) item.bean).getWeek());
                weekRecycler.setSize(0);
                SelectCourseRecyclerAdapter.RecyclerItem weekItem =
                        new SelectCourseRecyclerAdapter.RecyclerItem(SelectCourseRecyclerAdapter.WEEK,weekRecycler);
                mFilterItems.add(weekItem);
            } else {
                CourseRecycler courseRecycler = (CourseRecycler) item.bean;
                if (courseRecycler.getTeacher().contains(string) || courseRecycler.getName().contains(string)) {
                    if (weekRecycler != null) {
                        weekRecycler.setSize(weekRecycler.getSize() + 1);
                    }
                    mFilterItems.add(item);
                }
            }
        }
        mAdapter.notifyDataSetChanged();

    }

    public void setTitleChangeListener(OnTitleChangeListener listener) {
        mListener = listener;
    }

    /**
     * 当Fragment的标题改变时，让Activity修改Toolbar的标题
     */
    public interface OnTitleChangeListener {

        void changeToolbarTitle(String title);
    }

}
