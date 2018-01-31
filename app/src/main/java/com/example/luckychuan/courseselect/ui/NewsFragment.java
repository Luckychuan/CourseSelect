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

        mPresenter = new NewsPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestNewsTitle(LoginActivity.getUserKey(), mPage);

        //// TODO: 2018/1/31
        mPresenter.requestNewsContent(LoginActivity.getUserKey(),46);

    }

    @Override
    public void onResponse(News[] newsArray) {
        mPage++;
        mLayout.addData(newsArray);
    }

    @Override
    public void onResponse(NewsContent[] newsContent) {
        Log.d(TAG, "onResponse: " + newsContent[0].toString());
    }

    @Override
    public void onFail(String failMsg) {
        // TODO: 2018/1/31
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
