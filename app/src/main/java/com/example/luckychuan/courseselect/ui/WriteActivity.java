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
import com.example.luckychuan.courseselect.presenter.WriteMessagePresenter;
import com.example.luckychuan.courseselect.view.BooleanView;

/**
 * Created by Luckychuan on 2018/2/7.
 * 编写通知和消息的activity
 */

public class WriteActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, BooleanView {

    public static int TYPE_NOTIFICATION = 1;
    public static int TYPE_DEBATE = 2;
    private int mType;

    private EditText mTitle;
    private EditText mContent;

    private ProgressDialog mProgressDialog;
    private WriteMessagePresenter mPresenter;


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
        toolbar.inflateMenu(R.menu.menu_write_message);
        toolbar.setOnMenuItemClickListener(this);

        mTitle = (EditText) findViewById(R.id.edit_title);
        mContent = (EditText) findViewById(R.id.edit_content);

        mType = getIntent().getIntExtra("type",1);
        if (mType == TYPE_DEBATE) {
            toolbar.setTitle("编写新的消息");
            TextInputLayout titleLayout = (TextInputLayout) findViewById(R.id.title_layout);
            titleLayout.setVisibility(View.GONE);
        }


    }

    private void uploadNotification() {
        String title = mTitle.getText().toString();
        String content = mContent.getText().toString();
        if (title.length() == 0) {
            Toast.makeText(this, "标题不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (content.length() == 0) {
            Toast.makeText(this, "正文不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mPresenter == null) {
            mPresenter = new WriteMessagePresenter(this);
            mPresenter.attach(this);
        }
        //// TODO: 2018/2/7  课程编号
        mPresenter.uploadNotification(LoginActivity.getUserKey(), "201720081", title, content);
    }

    private void uploadDebate() {
        String content = mContent.getText().toString();
        if (content.length() == 0) {
            Toast.makeText(this, "正文不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        //// TODO: 2018/2/7 上传到服务器
        Toast.makeText(this, "上传", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.done) {
            if (mType == TYPE_NOTIFICATION) {
                uploadNotification();
            } else if (mType == TYPE_DEBATE) {
                uploadDebate();
            }
        }

        return true;
    }


    @Override
    public void showProgressbar() {
        mProgressDialog = ProgressDialog.show(this, "提示", "上传中");
    }

    @Override
    public void hideProgressbar() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onSuccess(boolean isUploadSuccess) {
        Toast.makeText(this, "上传成功！", Toast.LENGTH_SHORT).show();
        //// TODO: 2018/2/7 result
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
