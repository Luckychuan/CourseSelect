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

public class ShowMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ShowMoreAdapter";

    @LayoutRes
    private int mFooterViewId = -1;
    private RecyclerView.Adapter mInnerAdapter;

    public ShowMoreAdapter(RecyclerView.Adapter adapter){
        mInnerAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == mFooterViewId){
            return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(mFooterViewId, parent, false));
        }else{
            return mInnerAdapter.createViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) != mFooterViewId ){
            mInnerAdapter.bindViewHolder(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        if (mFooterViewId != -1) {
            return mInnerAdapter.getItemCount() + 1;
        }
        return mInnerAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mInnerAdapter.getItemCount()) {
            return mFooterViewId;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    public void addFooterView(@LayoutRes int id) {
        mFooterViewId = id;
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(View itemView) {
            super(itemView);
        }
    }


}
