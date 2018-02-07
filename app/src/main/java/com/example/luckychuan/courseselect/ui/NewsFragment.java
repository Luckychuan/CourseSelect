package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.courseselect.MoreRecyclerLayout;
import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.News;
import com.example.luckychuan.courseselect.bean.NewsContent;
import com.example.luckychuan.courseselect.presenter.NewsPresenter;
import com.example.luckychuan.courseselect.view.NewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class NewsFragment extends BaseFragment implements NewsView {

    private static final String TAG = "NewsFragment";
    private MoreRecyclerLayout mLayout;
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
        mLayout = (MoreRecyclerLayout) view.findViewById(R.id.more_layout);
        mLayout.setOnScrollBottomListener(new MoreRecyclerLayout.OnScrollBottomListener() {
            @Override
            public void OnScrollBottom() {
                mPresenter.requestNewsTitle(LoginActivity.getUserKey(), mPage);
            }
        });

        mPresenter = new NewsPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestNewsTitle(LoginActivity.getUserKey(), mPage);


    }

    @Override
    public void onResponse(News[] newsArray) {
        //没有数据的时候
        if (newsArray == null || newsArray.length == 0) {
            mLayout.showNoMoreView();
            mLayout.setOnScrollBottomListener(null);
            return;
        }

        //有数据时
        mPage++;
        Log.d(TAG, "onResponse: " + mPage);
        mLayout.addData(newsArray);
    }

    @Override
    public void onResponse(NewsContent[] newsContent) {
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        if (mPage > 1) {
            mLayout.hideLoadingView();
        }
        mLayout.setOnScrollBottomListener(null);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
