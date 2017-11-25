package com.example.luckychuan.courseselect.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Luckychuan on 2017/11/22.
 */

public abstract class CourseBaseViewHolder<T> extends RecyclerView.ViewHolder {

    public CourseBaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(T data);


}
