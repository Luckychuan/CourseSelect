package com.example.luckychuan.courseselect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.AttendanceCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luckychuan on 2018/3/8.
 */

public class CheckInfoRecyclerAdapter extends RecyclerView.Adapter<CheckInfoRecyclerAdapter.CheckViewHolder> {

    private ArrayList<AttendanceCheck> mList;
    private Context mContext ;
    private Map<String, Integer> mColors;
    private Map<String, String> mCheckTexts;

    public CheckInfoRecyclerAdapter(ArrayList<AttendanceCheck> list) {
        mList = list;

        mColors = new HashMap<>();
        mColors.put("OK", R.color.check_green);
        mColors.put("QJ", R.color.check_orange);
        mColors.put("CD", R.color.check_orange);
        mColors.put("KK", R.color.check_red);

        mCheckTexts = new HashMap<>();
        mCheckTexts.put("OK", "正常");
        mCheckTexts.put("QJ", "请假");
        mCheckTexts.put("CD", "迟到");
        mCheckTexts.put("KK", "旷课");

    }

    @Override
    public CheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CheckViewHolder(inflater.inflate(R.layout.recycler_attendance_check_info, parent, false));
    }

    @Override
    public void onBindViewHolder(CheckViewHolder holder, int position) {
        AttendanceCheck check = mList.get(position);
        holder.name.setText(check.getName());
        holder.account.setText(check.getAccount());
        holder.count.setText((position + 1) + "");
        holder.status.setText(mCheckTexts.get(check.getStatus()));
        holder.status.setTextColor(mContext.getColor(mColors.get(check.getStatus())));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class CheckViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView account;
        private TextView count;
        private TextView status;


        CheckViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            account = itemView.findViewById(R.id.account);
            count = itemView.findViewById(R.id.count);
            status = itemView.findViewById(R.id.status);

        }
    }


}
