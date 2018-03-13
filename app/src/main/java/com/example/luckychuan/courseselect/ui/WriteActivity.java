package com.example.luckychuan.courseselect.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.presenter.UploadPresenter;
import com.example.luckychuan.courseselect.view.BooleanView;

/**
 * Created by Luckychuan on 2018/2/7.
 * 编写通知和消息的activity
 */

public class WriteActivity extends UploadActivity implements Toolbar.OnMenuItemClickListener {

    private int mType;

    private EditText mTitle;
    private EditText mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);

        //初始化toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.course_toolbar);
        toolbar.setTitle("编写新的通知");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.menu_done);
        toolbar.setOnMenuItemClickListener(this);

        mTitle = (EditText) findViewById(R.id.edit_title);
        mContent = (EditText) findViewById(R.id.edit_content);

        mType = getIntent().getIntExtra("type",1);
        if (mType == MessageFragment.TYPE_DEBATE) {
            toolbar.setTitle("编写新的消息");
            TextInputLayout titleLayout = (TextInputLayout) findViewById(R.id.title_layout);
            titleLayout.setVisibility(View.GONE);
        }


    }

    private void uploadNotification() {
        String title = mTitle.getText().toString().trim();
        String content = mContent.getText().toString();
        if (title.length() == 0) {
            Toast.makeText(this, "标题不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (content.length() == 0) {
            Toast.makeText(this, "正文不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        getUploadPresenter().uploadNotification(LoginActivity.getUserKey(), getIntent().getStringExtra("course_id"), title, content);
    }

    private void uploadDebate() {
        String content = mContent.getText().toString();
        if (content.length() == 0) {
            Toast.makeText(this, "正文不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        getUploadPresenter().uploadDebate(LoginActivity.getUserKey(), getIntent().getStringExtra("course_id"), content);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.done) {
            if (mType == MessageFragment.TYPE_NOTIFICATION) {
                uploadNotification();
            } else if (mType == MessageFragment.TYPE_DEBATE) {
                uploadDebate();
            }
        }
        return true;
    }


}
