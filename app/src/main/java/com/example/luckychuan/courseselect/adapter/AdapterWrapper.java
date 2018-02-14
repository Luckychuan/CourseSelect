package com.example.luckychuan.courseselect.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Luckychuan on 2018/2/8.
 */

public class AdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "AdapterWrapper";
    @LayoutRes
    private int mHeaderViewType = -1;
    @LayoutRes
    private int mFooterViewType = -1;
    private View mHeaderView;
    private View mFooterView;
    private RecyclerView.Adapter mInnerAdapter;

    public AdapterWrapper(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == mFooterViewType) {
            if (mFooterView == null) {
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(mFooterViewType, parent, false));
            }
            return new ViewHolder(mFooterView);
        } else if (viewType == mHeaderViewType) {
            if (mHeaderView == null) {
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(mHeaderViewType, parent, false));
            }
            return new ViewHolder(mHeaderView);
        } else {
            return mInnerAdapter.createViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != mFooterViewType && getItemViewType(position) != mHeaderViewType) {
            mInnerAdapter.bindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        int count = mInnerAdapter.getItemCount();
        if (mHeaderViewType != -1) {
            count++;
        }
        if (mFooterViewType != -1) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        //返回HeaderView的类型
        if (position == getHeaderViewPosition()) {
            return mHeaderViewType;
        }
        //返回FooterView的类型
        if (position == getFooterViewPosition()) {
            return mFooterViewType;
        }

        //返回内部View的类型
        if (mHeaderViewType != -1) {
            position--;
        }
        return mInnerAdapter.getItemViewType(position);
    }


    private int getHeaderViewPosition() {
        if (mHeaderViewType != -1) {
            return 0;
        }
        return -1;
    }

    private int getFooterViewPosition() {
        if (mFooterViewType != -1) {
            if (mHeaderViewType != -1) {
                return mInnerAdapter.getItemCount() + 1;
            }
            return mInnerAdapter.getItemCount();
        }
        return -1;
    }


    public void addFooterView(@LayoutRes int viewType, View view) {
        mFooterView = view;
        mFooterViewType = viewType;
    }

    public void addFooterView(@LayoutRes int viewType) {
        mFooterViewType = viewType;
    }

    public void addHeaderView(@LayoutRes int viewType, View view) {
        mHeaderViewType = viewType;
        mHeaderView = view;
    }

    public void addHeaderView(@LayoutRes int viewType) {
        mHeaderViewType = viewType;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
