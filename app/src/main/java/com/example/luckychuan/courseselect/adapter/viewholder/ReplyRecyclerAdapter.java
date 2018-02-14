package com.example.luckychuan.courseselect.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.Reply;

import java.util.List;

/**
 * Created by Luckychuan on 2018/2/14.
 */

public class ReplyRecyclerAdapter extends RecyclerView.Adapter<ReplyRecyclerAdapter.ReplyViewHolder> {

    private List<Reply> mList;

    public ReplyRecyclerAdapter(List<Reply> list){
        mList = list;
    }

    @Override
    public ReplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReplyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_reply,parent,false));
    }

    @Override
    public void onBindViewHolder(ReplyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder{

        public ReplyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
