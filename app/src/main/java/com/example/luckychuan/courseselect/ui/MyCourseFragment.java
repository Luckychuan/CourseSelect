package com.example.luckychuan.courseselect.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.MyCourseJson;
import com.example.luckychuan.courseselect.presenter.MyCoursePresenter;
import com.example.luckychuan.courseselect.view.MyCourseView;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class MyCourseFragment extends Fragment implements MyCourseView {

    private static final String TAG = "MyCourseFragment";
    private MyCoursePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_course,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new MyCoursePresenter(this);
        mPresenter.attach(this);
        if(LoginActivity.getUser() == LoginActivity.STUDENT){
            mPresenter.requestMyCourse(LoginActivity.getStudent().getUserKey());
        }else if(LoginActivity.getUser() == LoginActivity.TEACHER){
            mPresenter.requestMyCourse(LoginActivity.getTeacher().getUserKey());
        }


    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void onError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String failMsg) {
        Toast.makeText(getContext(), failMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(MyCourseJson.Data[] data) {
        Log.d(TAG, "onSuccess: "+data[0].toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detach();
        }
    }
}
