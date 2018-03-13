package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.NewCheckRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.AttendanceCheck;
import com.example.luckychuan.courseselect.view.BooleanView;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Luckychuan on 2018/3/8.
 */

public class EditCheckActivity extends UploadActivity implements BooleanView {

    private ArrayList<AttendanceCheck> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_attendance_check_edit);

        final String courseId = getIntent().getStringExtra("course_id");
        final String date = getIntent().getStringExtra("date");

        //初始化toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.course_toolbar);
        if(date != null){
            toolbar.setTitle(date);
        }
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.menu_done);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.done:
                        doCommit(courseId,date);
                        break;
                }
                return false;
            }
        });

        mList = (ArrayList<AttendanceCheck>) getIntent().getSerializableExtra("list");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewCheckRecyclerAdapter adapter = new NewCheckRecyclerAdapter(mList);
        recyclerView.setAdapter(adapter);
    }

    private void doCommit(String courseId,String date) {
        if (mList == null || mList.size() == 0) {
            return;
        }

        String json = new Gson().toJson(mList);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
        getUploadPresenter().uploadAttendanceCheck(LoginActivity.getUserKey(), courseId, requestBody,date);
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "上传成功！", Toast.LENGTH_SHORT).show();
        setResult(1);
        finish();
    }

    @Override
    public void onFail(String failMsg) {
        super.onFail(failMsg);
        Log.d("fail_msg", "onFail: "+failMsg);
    }
}
