package com.example.luckychuan.courseselect.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.viewholder.CourseBaseViewHolder;
import com.example.luckychuan.courseselect.adapter.viewholder.CourseViewHolder;
import com.example.luckychuan.courseselect.adapter.viewholder.WeekViewHolder;
import com.example.luckychuan.courseselect.bean.CourseRecycler;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2017/11/22.
 */

public class SelectCourseRecyclerAdapter extends RecyclerView.Adapter<CourseBaseViewHolder> {

    public static final int WEEK = 1;
    public static final int COURSE = 2;

    private ArrayList<RecyclerItem> mList;

    public SelectCourseRecyclerAdapter(ArrayList<RecyclerItem> list){
        mList = list;
    }

    @Override
    public CourseBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CourseBaseViewHolder holder = null;
        View view;
        if(viewType == WEEK){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_week,parent,false);
            holder = new WeekViewHolder(view);
        }else if(viewType == COURSE){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_course,parent,false);
            holder = new CourseViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(CourseBaseViewHolder holder, int position) {
        holder.bindViewHolder(mList.get(position).bean);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    public static class RecyclerItem<T> {
        public int type;
        public T bean;

        public RecyclerItem(int type, T bean) {
            this.type = type;
            this.bean = bean;
        }
    }


}
