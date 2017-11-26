package com.example.luckychuan.courseselect.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.WeekRecycler;

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
        switch (data.getWeek()) {
            case 1:
                mWeek.setText("星期一");
                break;
            case 2:
                mWeek.setText("星期二");
                break;
            case 3:
                mWeek.setText("星期三");
                break;
            case 4:
                mWeek.setText("星期四");
                break;
            case 5:
                mWeek.setText("星期五");
                break;
        }

        mSize.setText(data.getSize() + "门课程");


    }
}
