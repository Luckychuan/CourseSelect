package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.MyCourseRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.MyCourse;
import com.example.luckychuan.courseselect.presenter.MyCoursePresenter;
import com.example.luckychuan.courseselect.view.MyCourseView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class MyCourseFragment extends BaseFragment implements MyCourseView {

    private static final String TAG = "MyCourseFragment";
    private MyCoursePresenter mPresenter;
    private MyCourseRecyclerAdapter mAdapter;
    private ArrayList<MyCourse> mList;
    private static Set<String> mTags = new HashSet<>();

    private TextView mNoCourse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_course, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNoCourse = view.findViewById(R.id.no_course);

        mPresenter = new MyCoursePresenter(this);
        mPresenter.attach(this);
        mPresenter.requestCourse(LoginActivity.getUserKey());

        mList = new ArrayList<>();
        mAdapter = new MyCourseRecyclerAdapter(mList);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_my_course);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);


    }


    @Override
    public void onSuccess(MyCourse[] myCourses) {
        mList.clear();
        Collections.addAll(mList, myCourses);
        if (mList.size() == 0) {
            mNoCourse.setVisibility(View.VISIBLE);
            return;
        }
        mAdapter.notifyDataSetChanged();

        //若是学生，注册推送通知Tag
        if (LoginActivity.getUser() == LoginActivity.STUDENT) {
            for (MyCourse myCourse : mList) {
                mTags.add(myCourse.getId());
            }
            JPushInterface.setTags(getContext(), BaseActivity.COURSE_TAG, mTags);
        }
    }

    public static Set<String> getTags() {
        return mTags;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
