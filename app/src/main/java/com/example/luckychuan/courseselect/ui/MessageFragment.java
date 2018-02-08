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
import com.example.luckychuan.courseselect.adapter.MessageRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class MessageFragment extends BaseFragment {

    public static int TYPE_NOTIFICATION = 1;
    public static int TYPE_DEBATE = 2;

    private List<ItemBean> mList;
    private MessageRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //判断当前功能是通知还是讨论
        int type = getArguments().getInt("function");

        mList = new ArrayList<>();
        ItemBean itemBean = new ItemBean(MessageRecyclerAdapter.TYPE_HEADER, null);
        mList.add(itemBean);
        //学生没有编写新通知的功能
        if (type == TYPE_NOTIFICATION && LoginActivity.getUser() == LoginActivity.STUDENT) {
            mList.remove(itemBean);
        }


        //// TODO: 2018/2/8 test
        for (int i = 0; i < 15; i++) {
            ItemBean itemBean1 = new ItemBean(MessageRecyclerAdapter.TYPE_CONTENT, null);
            mList.add(itemBean1);
        }


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MessageRecyclerAdapter(mList, type);
        recyclerView.setAdapter(mAdapter);

    }


}
