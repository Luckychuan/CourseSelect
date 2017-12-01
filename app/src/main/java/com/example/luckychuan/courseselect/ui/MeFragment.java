package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class MeFragment extends Fragment {

    private OnLogoutListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.logout_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onLogout();
                }
            }
        });

        TextView textView = view.findViewById(R.id.tv_user_info);
        if(LoginActivity.getUser() == LoginActivity.STUDENT){
            textView.setText(LoginActivity.getStudent().toString());
        }else if(LoginActivity.getUser() == LoginActivity.TEACHER){
            textView.setText(LoginActivity.getTeacher().toString());
        }
    }

    public void setOnLogoutListener(OnLogoutListener listener) {
        mListener = listener;
    }

    interface OnLogoutListener {
        void onLogout();
    }

}
