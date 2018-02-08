package com.example.luckychuan.courseselect.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.MessageRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.ItemBean;
import com.example.luckychuan.courseselect.bean.Message;
import com.example.luckychuan.courseselect.presenter.MessagePresenter;
import com.example.luckychuan.courseselect.view.MessageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class MessageFragment extends BaseFragment implements MessageView{

    public static int TYPE_NOTIFICATION = 1;
    public static int TYPE_DEBATE = 2;
    //判断当前功能是通知还是讨论
    private int mType;

    public static int REQUEST_CODE = 10001;

    private List<ItemBean> mList;
    private MessageRecyclerAdapter mAdapter;

    private MessagePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mType = getArguments().getInt("function");

        mList = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MessageRecyclerAdapter(mList, mType);
        recyclerView.setAdapter(mAdapter);

        mPresenter = new MessagePresenter(this);
        mPresenter.attach(this);
        if(mType == TYPE_NOTIFICATION){
            mPresenter.getNotification(LoginActivity.getUserKey(),getArguments().getString("course_id"));
        }else{
            //// TODO: 2018/2/8
        }

    }


    @Override
    public void onSuccess(Message[] messages) {
        mList.clear();

        ItemBean itemBean = new ItemBean(MessageRecyclerAdapter.TYPE_HEADER, null);
        mList.add(itemBean);
        //学生没有编写新通知的功能
        if (mType == TYPE_NOTIFICATION && LoginActivity.getUser() == LoginActivity.STUDENT) {
            mList.remove(itemBean);
        }

        for (int i = 0; i < messages.length; i++) {
            ItemBean bean = new ItemBean(MessageRecyclerAdapter.TYPE_CONTENT,messages[i]);
            mList.add(bean);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null){
            if(mType == TYPE_NOTIFICATION){
                mPresenter.getNotification(LoginActivity.getUserKey(),getArguments().getString("course_id"));
            }else{
                //// TODO: 2018/2/8
            }
        }
    }
}
