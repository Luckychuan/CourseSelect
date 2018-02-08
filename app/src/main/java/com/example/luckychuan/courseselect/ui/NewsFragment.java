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
import com.example.luckychuan.courseselect.adapter.NewsRecyclerAdapter;
import com.example.luckychuan.courseselect.adapter.ShowMoreAdapter;
import com.example.luckychuan.courseselect.bean.News;
import com.example.luckychuan.courseselect.bean.NewsContent;
import com.example.luckychuan.courseselect.presenter.NewsPresenter;
import com.example.luckychuan.courseselect.view.NewsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class NewsFragment extends BaseFragment implements NewsView {

    private static final String TAG = "NewsFragment";
    private List<News> mList = new ArrayList<>();
    private ShowMoreAdapter mAdapter;
    private NewsPresenter mPresenter;
    private int mPage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new NewsPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestNewsTitle(LoginActivity.getUserKey(), mPage);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ShowMoreAdapter(new NewsRecyclerAdapter(mList));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //判断RecyclerView是否滑动到最底端
                //滑动过的距离=偏移量+当前view显示的区域
                int total = recyclerView.computeVerticalScrollOffset() + recyclerView.computeVerticalScrollExtent();
                if (total >= recyclerView.computeVerticalScrollRange()) {
                    //滑动到最底端
                    if (mPage != -1) {
                        mPresenter.requestNewsTitle(LoginActivity.getUserKey(), mPage);
                    }
                }
            }
        });
    }

    @Override
    public void onResponse(News[] newsArray) {
        //没有数据的时候
        if (newsArray == null || newsArray.length == 0) {
            mAdapter.addFooterView(R.layout.recycler_no_more);
            mAdapter.notifyDataSetChanged();
            return;
        }

        //有数据时
        mPage++;
        Collections.addAll(mList, newsArray);
        mAdapter.addFooterView(R.layout.recycler_loading);
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void onResponse(NewsContent[] newsContent) {
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        mPage = -1;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
