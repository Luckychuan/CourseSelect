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
 * 使用RecyclerView和footerView组合成的“加载更多”的View
 */

public class MoreRecyclerLayout extends RelativeLayout {

    private static final String TAG = "MoreRecyclerLayout";

    private NewsRecyclerAdapter mAdapter;
    private List<NewsRecyclerAdapter.ItemBean> mList;
    private OnScrollBottomListener mListener;

    public MoreRecyclerLayout(Context context) {
        super(context);
    }

    public MoreRecyclerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mList = new ArrayList<>();
        mAdapter = new NewsRecyclerAdapter(mList);

        RecyclerView recyclerView = new RecyclerView(context);
        ViewGroup.LayoutParams params = new ViewGroup
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(recyclerView, params);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
                   if(mListener != null){
                       mListener.OnScrollBottom();
                   }
                }
            }
        });
    }

    public MoreRecyclerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addData(News[] list) {
        //移除上一次加载时显示的LoadingView
        removeLoading();

        //添加新数据
        for (News news : list) {
            NewsRecyclerAdapter.ItemBean item = new NewsRecyclerAdapter
                    .ItemBean(NewsRecyclerAdapter.TYPE_NEWS, news);
            mList.add(item);
        }

        //在最底部添加loadingView
        NewsRecyclerAdapter.ItemBean item = new NewsRecyclerAdapter
                .ItemBean(NewsRecyclerAdapter.TYPE_LOADING, null);
        mList.add(item);

        mAdapter.notifyDataSetChanged();
    }

    private boolean removeLoading(){
        boolean isRemove = false;
        if (mList.size() > 0) {
            NewsRecyclerAdapter.ItemBean lastItem = (NewsRecyclerAdapter.ItemBean) mList.get(mList.size() - 1);
            if (lastItem.type == NewsRecyclerAdapter.TYPE_LOADING) {
                mList.remove(lastItem);
                isRemove = true;
            }
        }
        return isRemove;
    }

    public void hideLoadingView(){
        boolean isRemove = removeLoading();
        if(isRemove){
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setOnScrollBottomListener(OnScrollBottomListener listener){
        mListener = listener;
    }

    public void showNoMoreView(){
        removeLoading();

        NewsRecyclerAdapter.ItemBean item = new NewsRecyclerAdapter
                .ItemBean(NewsRecyclerAdapter.TYPE_NO_MORE, null);
        mList.add(item);

        mAdapter.notifyDataSetChanged();

    }


    /**
     * 当RecyclerView滑动到最底部
     */
    public interface OnScrollBottomListener{
        void OnScrollBottom();
    }


}
