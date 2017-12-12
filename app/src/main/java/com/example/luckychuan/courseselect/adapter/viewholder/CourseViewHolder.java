package com.example.luckychuan.courseselect.adapter.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.CourseRecycler;

/**
 * Created by Luckychuan on 2017/11/22.
 */

public class CourseViewHolder extends CourseBaseViewHolder<CourseRecycler> {

    private static final String TAG = "CourseViewHolder";
    private TextView mLevel;
    private TextView mTime;
    private TextView mTeacher;
    private TextView mName;
    private TextView mStudentLeft;

    public CourseViewHolder(View itemView, final OnItemClickListener listener) {
        super(itemView);
        mLevel = (TextView) itemView.findViewById(R.id.tv_level);
        mTime = (TextView) itemView.findViewById(R.id.tv_classTime);
        mTeacher = (TextView) itemView.findViewById(R.id.tv_teacher);
        mName = (TextView) itemView.findViewById(R.id.tv_course_name);
        mStudentLeft = (TextView) itemView.findViewById(R.id.tv_studentLeft);

        LinearLayout itemLayout= (LinearLayout) itemView.findViewById(R.id.item_layout);
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.OnItemClick(getLayoutPosition());
                }
            }
        });

    }

    @Override
    public void bindViewHolder(CourseRecycler data) {
//        Log.d(TAG, "bindViewHolder: "+data.toString());
        mLevel.setText("等级" + data.getLevel());
        mTime.setText(data.getTime());
        mTeacher.setText(data.getTeacher());
        mName.setText(data.getName());
        mStudentLeft.setText(data.getStudentLeft());
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

}
