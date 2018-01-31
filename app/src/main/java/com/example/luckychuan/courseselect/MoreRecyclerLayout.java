package com.example.luckychuan.courseselect;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.luckychuan.courseselect.adapter.NewsRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class MoreRecyclerLayout extends RelativeLayout {

    private static final String TAG = "MoreRecyclerLayout";

    private NewsRecyclerAdapter mAdapter;
    private List<NewsRecyclerAdapter.ItemBean> mList ;

    public MoreRecyclerLayout(Context context) {
        super(context);
    }

    public MoreRecyclerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mList  = new ArrayList<>();
        mAdapter = new NewsRecyclerAdapter(mList);

        RecyclerView recyclerView = new RecyclerView(context);
        ViewGroup.LayoutParams params = new ViewGroup
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(recyclerView, params);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    public MoreRecyclerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addData(List<News> list){
        for(News news : list){
            NewsRecyclerAdapter.ItemBean item = new NewsRecyclerAdapter
                    .ItemBean(NewsRecyclerAdapter.TYPE_NEWS, news);

            mList.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }



}
