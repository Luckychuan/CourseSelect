package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.presenter.SelectCoursePresenter;
import com.example.luckychuan.courseselect.view.SelectCourseView;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2017/11/20.
 */

public class CourseSelectFragment extends Fragment implements SelectCourseView {

    private static final String TAG = "CourseSelectFragment";
    private static final int[] LEVELS = {1, 2, 3, 4};

    private String mCampus;
    private SelectCoursePresenter mPresenter;
    private ArrayList<CourseJson> mCourses;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frament_course_select, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCampus = getArguments().getString("campus");
        mPresenter = new SelectCoursePresenter(this);
        mPresenter.attach(this);
        mCourses = new ArrayList<>();

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
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "toastErrorMsg: "+errorMsg);
    }

    @Override
    public void requestSelectCourse() {
        for (int i = 0; i < LEVELS.length; i++) {
            mPresenter.requestData(LEVELS[i], mCampus);
//            Log.d(TAG, "level:"+LEVELS[i]+" campus:"+mCampus);
        }
    }

    @Override
    public void loadSelectCourseUI(CourseJson[] courseJson) {
        for(CourseJson c:courseJson){
            Log.d(TAG, c.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
