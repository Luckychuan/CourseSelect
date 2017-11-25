package com.example.luckychuan.courseselect.adapter.viewholder;

import android.util.Log;
import android.view.View;
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

    public CourseViewHolder(View itemView) {
        super(itemView);

        mLevel = (TextView) itemView.findViewById(R.id.tv_level);
        mTime = (TextView) itemView.findViewById(R.id.tv_classTime);
        mTeacher = (TextView) itemView.findViewById(R.id.tv_teacher);
        mName = (TextView) itemView.findViewById(R.id.tv_course_name);
        mStudentLeft = (TextView) itemView.findViewById(R.id.tv_studentLeft);

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
}
