package com.example.luckychuan.courseselect.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.AttendanceCheck;

import java.util.ArrayList;

/**
 * Created by Luckychuan on 2018/3/8.
 */

public class NewCheckRecyclerAdapter extends RecyclerView.Adapter<NewCheckRecyclerAdapter.CheckViewHolder> {

    private ArrayList<AttendanceCheck> mList;

    public NewCheckRecyclerAdapter(ArrayList<AttendanceCheck> list) {
        mList = list;
    }

    @Override
    public CheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CheckViewHolder(inflater.inflate(R.layout.recycler_attendance_check_edit, parent, false));
    }

    @Override
    public void onBindViewHolder(CheckViewHolder holder, int position) {
        AttendanceCheck check = mList.get(position);
        holder.name.setText(check.getName());
        holder.account.setText(check.getAccount());
        holder.count.setText((position + 1) + "");
        holder.radioGroup.check(getStatusId(check.getStatus()));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private String getStatusCode(int id){
        switch (id){
            case R.id.normal: return "OK";
            case R.id.leave: return "QJ";
            case R.id.absence: return "KK";
            case R.id.late: return "CD";
        }
        return "";
    }

    private int getStatusId(String statusCode){
        if(statusCode.equals("OK")){
            return R.id.normal;
        }else if(statusCode.equals("CD")){
            return R.id.late;
        }else if(statusCode.equals("QJ")){
            return R.id.leave;
        }else if(statusCode.equals("KK")){
            return R.id.absence;
        }else{
            return -1;
        }
    }

    class CheckViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView account;
        private TextView count;
        private RadioGroup radioGroup;

        CheckViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            account = itemView.findViewById(R.id.account);
            count = itemView.findViewById(R.id.count);
            radioGroup = itemView.findViewById(R.id.radio_group);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    AttendanceCheck check =  mList.get(getLayoutPosition());
                    int id = radioGroup.getCheckedRadioButtonId();
                    check.setStatus(getStatusCode(id));
                }
            });

        }

    }


}
