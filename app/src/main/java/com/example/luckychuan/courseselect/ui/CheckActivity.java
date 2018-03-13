package com.example.luckychuan.courseselect.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.CheckInfoRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.AttendanceCheck;
import com.example.luckychuan.courseselect.bean.Date;
import com.example.luckychuan.courseselect.presenter.AttendanceCheckPresenter;
import com.example.luckychuan.courseselect.view.AttendanceCheckView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 考勤记录界面
 */

public class CheckActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, AttendanceCheckView {

    private static final String TAG = "CheckActivity";
    private ArrayList<AttendanceCheck> mChecks = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();
    private AttendanceCheckPresenter mPresenter;
    private CheckInfoRecyclerAdapter mAdapter;
    private String mSelectedDate;
    private String mCourseId;
    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpinnerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_check);

        mCourseId = getIntent().getStringExtra("course_id");

        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.menu_attendance_check);
        toolbar.setOnMenuItemClickListener(this);

        mSpinner = (AppCompatSpinner) findViewById(R.id.spinner);
        mSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.custom_spiner_text_item, mDates);
        mSpinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedDate = mSpinner.getSelectedItem().toString();
                mPresenter.getAttendanceChecks(LoginActivity.getUserKey(), mCourseId, mSelectedDate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CheckInfoRecyclerAdapter(mChecks);
        recyclerView.setAdapter(mAdapter);

        mPresenter = new AttendanceCheckPresenter(this);
        mPresenter.attach(this);
        mPresenter.getAttendanceCheckDates(LoginActivity.getUserKey(), mCourseId);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_check:
                List<AttendanceCheck> checks = new ArrayList<>();
                for (AttendanceCheck ac : mChecks) {
                    AttendanceCheck check = new AttendanceCheck();
                    check.setName(ac.getName());
                    check.setAccount(ac.getAccount());
                    check.setStatus("OK");
                    checks.add(check);
                }
                Intent intent = new Intent(this, EditCheckActivity.class);
                intent.putExtra("course_id", mCourseId);
                intent.putExtra("list", (Serializable) checks);
                startActivityForResult(intent, 0);
                break;
            case R.id.edit_check:
                if (mSelectedDate == null) {
                    Toast.makeText(this, "还没有考勤记录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent1 = new Intent(this, EditCheckActivity.class);
                    intent1.putExtra("date", mSelectedDate);
                    intent1.putExtra("course_id", mCourseId);
                    intent1.putExtra("list", (Serializable) mChecks);
                    startActivityForResult(intent1, 0);
                }

                break;

        }
        return true;
    }


    @Override
    public void onSuccess(Date[] dates) {
        mDates.clear();
        for (Date d : dates) {
            mDates.add(d.getDate());
        }
        mSpinnerAdapter.notifyDataSetChanged();

        if (mDates.size() > 0) {
            mSpinner.setSelection(0);
        }
        if (mDates.size() == 0) {
            TextView textView = (TextView) findViewById(R.id.no_data);
            textView.setVisibility(View.VISIBLE);
            mPresenter.getAttendanceChecks(LoginActivity.getUserKey(), mCourseId, null);
        }
    }

    @Override
    public void onSuccess(AttendanceCheck[] checks) {
        mChecks.clear();
        TextView textView = (TextView) findViewById(R.id.no_data);
        textView.setVisibility(View.GONE);
        Collections.addAll(mChecks, checks);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStudentInfoSuccess(AttendanceCheck[] checks) {
        for (AttendanceCheck check : checks) {
            check.setStatus("OK");
            mChecks.add(check);
            Log.d(TAG, "onStudentInfoSuccess: " + check.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 1) {
                mPresenter.getAttendanceCheckDates(LoginActivity.getUserKey(), mCourseId);
            }
        }
    }
}
