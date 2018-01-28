package com.example.luckychuan.courseselect.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.StudentMyCourse;
import com.example.luckychuan.courseselect.ui.CourseActivity;
import com.example.luckychuan.courseselect.util.Constant;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public class MyCourseRecyclerAdapter extends RecyclerView.Adapter<MyCourseRecyclerAdapter.MyViewHolder> {

    private ArrayList<StudentMyCourse.Data> mList;
    private Context mContext;

    public MyCourseRecyclerAdapter(ArrayList<StudentMyCourse.Data> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_my_course, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView teacher;
        private TextView duration;
        private TextView time;
        private TextView campus;
        private TextView score;
        private LinearLayout item;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.course);
            teacher = (TextView) itemView.findViewById(R.id.teacher);
            duration = (TextView) itemView.findViewById(R.id.duration);
            time = (TextView) itemView.findViewById(R.id.time);
            campus = (TextView) itemView.findViewById(R.id.campus);
            item = (LinearLayout) itemView.findViewById(R.id.item_view);
            score = itemView.findViewById(R.id.score);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, CourseActivity.class);
                    intent.putExtra("course_id",mList.get(getLayoutPosition()).getId());
                    mContext.startActivity(intent);
                }
            });
        }

        public void onBind(StudentMyCourse.Data data) {
            name.setText(data.getName());
            teacher.setText("任课教师：" + data.getTeacher());
            duration.setText("周期：" + data.getDuration()+"周");
            time.setText("上课时间：" + Constant.WEEKS[data.getWeek()] + "，第" + data.getTime() + "、" + (data.getTime() + 1) + "节");
            campus.setText("校区："+data.getCampus());

            if(data.getCommit().equals("已提交")){
                score.setVisibility(View.VISIBLE);
            }else{
                score.setVisibility(View.GONE);
            }

            if(data.getRate() == 1){
                score.setText("分数："+data.getScore());
            }else{
                score.setText("分数："+"尚未评教");
            }
        }


    }

}
