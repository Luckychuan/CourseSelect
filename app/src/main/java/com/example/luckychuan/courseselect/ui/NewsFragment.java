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
    private RecyclerView mRecyclerView;

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
        mPresenter.requestNewsTitle(LoginActivity.getUserKey(), mPage++);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ShowMoreAdapter(new NewsRecyclerAdapter(mList));
        mRecyclerView.setAdapter(mAdapter);
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
        Collections.addAll(mList, newsArray);
        mAdapter.notifyDataSetChanged();
        //监听RecyclerView滑动到最底端
        mRecyclerView.addOnScrollListener(mListener);

    }

    @Override
    public void onResponse(NewsContent[] newsContent) {
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
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
                    mAdapter.addFooterView(R.layout.recycler_loading);
                    isFooterViewExist = true;
                    mAdapter.notifyDataSetChanged();
                }
                //判断是否在最底部
                if (!recyclerView.canScrollVertically(1)) {
                    //滑动到最底端
                    mPresenter.requestNewsTitle(LoginActivity.getUserKey(), mPage++);
                    mRecyclerView.removeOnScrollListener(mListener);
                    isFooterViewExist = false;
                }
            }

        }
    };
}
