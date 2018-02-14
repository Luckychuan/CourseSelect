package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.AdapterWrapper;
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

public class MessageFragment extends BaseFragment implements MessageView {

    public static int TYPE_NOTIFICATION = 1;
    public static int TYPE_DEBATE = 2;
    //判断当前功能是通知还是讨论
    private int mType;

    private List<ItemBean> mList;
    private AdapterWrapper mAdapter;
    private MessagePresenter mPresenter;
    private int mPage;
    private RecyclerView mRecyclerView;


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

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new AdapterWrapper(new MessageRecyclerAdapter(mList, mType, getArguments().getString("course_id")));
        mRecyclerView.setAdapter(mAdapter);
        if (mType == TYPE_DEBATE) {

        }
        mPresenter = new MessagePresenter(this);
        mPresenter.attach(this);
    }

    @Override
    public void onNotificationSuccess(Message[] messages) {
        for (int i = 0; i < messages.length; i++) {
            ItemBean bean = new ItemBean(MessageRecyclerAdapter.TYPE_CONTENT, messages[i]);
            mList.add(bean);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDebateSuccess(Message[] messages) {

        //没有数据的时候
        if (messages == null || messages.length == 0) {
//            mAdapter.addFooterView(R.layout.recycler_no_more);
            mAdapter.notifyDataSetChanged();
            return;
        }

        for (int i = 0; i < messages.length; i++) {
            ItemBean bean = new ItemBean(MessageRecyclerAdapter.TYPE_CONTENT, messages[i]);
            mList.add(bean);
        }
        //监听RecyclerView滑动到最底端
        mRecyclerView.addOnScrollListener(mListener);
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
        mList.clear();

        ItemBean itemBean = new ItemBean(MessageRecyclerAdapter.TYPE_HEADER, null);
        mList.add(itemBean);
        //学生没有编写新通知的功能
        if (LoginActivity.getUser() == LoginActivity.STUDENT && mType == TYPE_NOTIFICATION) {
            mList.remove(itemBean);
        }
        if (mPresenter != null) {
            if (mType == TYPE_NOTIFICATION) {
                mPresenter.getNotification(LoginActivity.getUserKey(), getArguments().getString("course_id"));
            } else {
                mPage = 1;
                mPresenter.getDebate(LoginActivity.getUserKey(), getArguments().getString("course_id"), mPage++);
            }
        }
    }

    private RecyclerView.OnScrollListener mListener = new RecyclerView.OnScrollListener() {
        //判断正在加载的View是否已经显示
        private boolean isFooterViewExist = false;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            //判断是否已经滑动过
            if (recyclerView.computeVerticalScrollOffset() > 0) {

                //显示正在加载界面到最底端
                if (!isFooterViewExist) {
//                    mAdapter.addFooterView(R.layout.recycler_loading);
                    isFooterViewExist = true;
                    mAdapter.notifyDataSetChanged();
                }
                //判断是否在最底部
                if (!recyclerView.canScrollVertically(1)) {
                    //滑动到最底端
                    mPresenter.getDebate(LoginActivity.getUserKey(), getArguments().getString("course_id"), mPage++);
                    mRecyclerView.removeOnScrollListener(mListener);
                    isFooterViewExist = false;
                }
            }

        }
    };


}
