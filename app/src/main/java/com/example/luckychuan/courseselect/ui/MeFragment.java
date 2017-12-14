package com.example.luckychuan.courseselect.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.LogoutJson;
import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;
import com.example.luckychuan.courseselect.presenter.LogoutPresenter;
import com.example.luckychuan.courseselect.util.Constant;
import com.example.luckychuan.courseselect.view.LogoutView;


public class MeFragment extends Fragment implements LogoutView {

    private LinearLayout mItemLayout;
    private LogoutPresenter mPresenter;

    private OnLogoutListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mItemLayout = view.findViewById(R.id.item_layout);

        if (LoginActivity.getUser() == LoginActivity.STUDENT) {
            initStudentView(LoginActivity.getStudent());
        } else if (LoginActivity.getUser() == LoginActivity.TEACHER) {
            initTeacherView(LoginActivity.getTeacher());
        }
    }

    private void initStudentView(StudentJson.Data student) {
        String[] infoArray = new String[]{student.getName(), student.getId(), student.getSex(), student.getAcademy(), student.getMajor(), student.getStudentClass()};
        for (int i = 0; i < Constant.STUDENT_INFO_ITEM.length; i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_user_info, mItemLayout, false);
            TextView itemTextView = (TextView) view.findViewById(R.id.tv_item_name);
            TextView infoTextView = (TextView) view.findViewById(R.id.tv_item_info);
            itemTextView.setText(Constant.STUDENT_INFO_ITEM[i]);
            infoTextView.setText(infoArray[i]);
            mItemLayout.addView(view);
        }
        addButtonView();
    }

    private void initTeacherView(TeacherJson.Data teacher) {
        String[] infoArray = new String[]{teacher.getName(), teacher.getId(), teacher.getXingBie(), teacher.getChuShengRiQi(), teacher.getBuMen()
                , teacher.getKeShi(), teacher.getZhiCheng(), teacher.getXueLi()};
        for (int i = 0; i < Constant.TEACHER_INFO_ITEM.length; i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_user_info, mItemLayout, false);
            TextView itemTextView = (TextView) view.findViewById(R.id.tv_item_name);
            TextView infoTextView = (TextView) view.findViewById(R.id.tv_item_info);
            itemTextView.setText(Constant.TEACHER_INFO_ITEM[i]);
            infoTextView.setText(infoArray[i]);
            mItemLayout.addView(view);
        }
        addButtonView();
    }

    private void addButtonView() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(56));
        layoutParams.setMargins(dpToPx(8), dpToPx(16), dpToPx(8), 0);
        Button button = new Button(getContext());
        button.setText("注销");
        button.setBackgroundResource(R.drawable.radius_button);
        button.setTextColor(Color.WHITE);
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter = new LogoutPresenter(MeFragment.this);
                mPresenter.attach(MeFragment.this);
                if (LoginActivity.getUser() == LoginActivity.TEACHER) {
                    mPresenter.requestLogout(LoginActivity.getTeacher().getUserKey());
                } else if (LoginActivity.getUser() == LoginActivity.STUDENT) {
                    mPresenter.requestLogout(LoginActivity.getStudent().getUserKey());
                }
            }
        });
        mItemLayout.addView(button);
    }

    private int dpToPx(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public void setOnLogoutListener(OnLogoutListener listener) {
        mListener = listener;
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
        if (mListener != null) {
            mListener.onLogout();
        }
    }

    @Override
    public void onFail(String failMsg) {
        if (mListener != null) {
            mListener.onLogout();
        }
    }

    @Override
    public void onResponse(LogoutJson json) {
        if (mListener != null) {
            mListener.onLogout();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    interface OnLogoutListener {
        void onLogout();
    }

}
