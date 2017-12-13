package com.example.luckychuan.courseselect.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.MyCourseJson;
import com.example.luckychuan.courseselect.util.Constant;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public class MyCourseRecyclerAdapter extends RecyclerView.Adapter<MyCourseRecyclerAdapter.MyViewHolder> {

    private ArrayList<MyCourseJson.Data> mList;

    public MyCourseRecyclerAdapter(ArrayList<MyCourseJson.Data> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        private TextView week;
        private TextView time;
        private TextView campus;
        private LinearLayout item;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.course);
            teacher = (TextView) itemView.findViewById(R.id.teacher);
            duration = (TextView) itemView.findViewById(R.id.duration);
            week = (TextView) itemView.findViewById(R.id.week);
            time = (TextView) itemView.findViewById(R.id.time);
            campus = (TextView) itemView.findViewById(R.id.campus);
            item = (LinearLayout) itemView.findViewById(R.id.item_view);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void onBind(MyCourseJson.Data data) {
            name.setText(data.getName());
            teacher.setText(data.getTeacher());
            duration.setText(data.getDuration());
            week.setText(Constant.WEEKS[data.getWeek()]);
            time.setText("第" + data.getTime() + "节");
            campus.setText(data.getCampus());
        }


    }

}
