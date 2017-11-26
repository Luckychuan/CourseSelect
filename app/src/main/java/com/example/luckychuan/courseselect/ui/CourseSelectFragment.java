package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.SelectCourseRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.bean.CourseRecycler;
import com.example.luckychuan.courseselect.presenter.SelectCoursePresenter;
import com.example.luckychuan.courseselect.test.TestJsonData;
import com.example.luckychuan.courseselect.util.FormatUtil;
import com.example.luckychuan.courseselect.view.SelectCourseView;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2017/11/20.
 */

public class CourseSelectFragment extends Fragment implements SelectCourseView {

    private static final String TAG = "CourseSelectFragment";
    private static final int[] LEVELS = {1, 2, 3, 4};

    private String mCampus;
    private SelectCoursePresenter mPresenter;
    //http返回来的数据集
    private ArrayList<CourseJson> mCourseJsons;

    //RecyclerView要用的的List
    private ArrayList<SelectCourseRecyclerAdapter.RecyclerItem> mRecyclerItems;
    private SelectCourseRecyclerAdapter mAdapter;

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

        mCourseJsons = new ArrayList<>();
        mRecyclerItems = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_course);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SelectCourseRecyclerAdapter(mRecyclerItems);
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.float_button);
        button.attachToRecyclerView(recyclerView);


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
        Log.d(TAG, "toastErrorMsg: "+errorMsg);

//        //// TODO: 2017/11/25 test使用的假数据
//        for (CourseJson c : TestJsonData.getTestCourseData()) {
////            Log.d(TAG, "e: "+ c.toString());
//            mCourseJsons.add(c);
//        }
    }

    @Override
    public void requestSelectCourse() {
       mPresenter.requestData(LEVELS,mCampus);

//        //// TODO: 2017/11/25 test 只加载一次
//        if (mCampus.equals("曲江校区")) {
//            mPresenter.requestData(LEVELS[0], mCampus);
//        }


    }

    @Override
    public void addData(CourseJson[] courseJson) {
        for (CourseJson c : courseJson) {
            mCourseJsons.add(c);
        }

        SelectCourseRecyclerAdapter.RecyclerItem<String> weekItem =
                new SelectCourseRecyclerAdapter.RecyclerItem<>(SelectCourseRecyclerAdapter.WEEK, "");
        mRecyclerItems.add(weekItem);

        //添加RecyclerView使用的Items
        for (CourseJson cj : mCourseJsons) {
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
            }

        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadSelectCourseUI() {
        Log.d("rx_json", "loadSelectCourseUI: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    private void refreshCourse() {
        //// TODO: 2017/11/22
        mCourseJsons.clear();
    }


}
