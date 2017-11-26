package com.example.luckychuan.courseselect.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.WeekRecycler;
import com.example.luckychuan.courseselect.util.Constant;

/**
 * Created by Luckychuan on 2017/11/25.
 */

public class WeekViewHolder extends CourseBaseViewHolder<WeekRecycler> {

    private TextView mWeek;
    private TextView mSize;

    public WeekViewHolder(View itemView) {
        super(itemView);
        mWeek = (TextView) itemView.findViewById(R.id.tv_week);
        mSize = (TextView) itemView.findViewById(R.id.tv_size);
    }

    @Override
    public void bindViewHolder(WeekRecycler data) {
        mWeek.setText(Constant.WEEK[data.getWeek() - 1]);
        mSize.setText(data.getSize() + "门课程");


    }
}
