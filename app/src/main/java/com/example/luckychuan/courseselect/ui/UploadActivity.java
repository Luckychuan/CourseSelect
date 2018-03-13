package com.example.luckychuan.courseselect.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.luckychuan.courseselect.presenter.UploadPresenter;
import com.example.luckychuan.courseselect.view.BooleanView;

/**
 * Created by Luckychuan on 2018/3/9.
 */

public abstract class UploadActivity extends BaseActivity implements BooleanView {

    private ProgressDialog mProgressDialog;
    private UploadPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = new UploadPresenter(this);
            mPresenter.attach(this);
        }
    }

    public UploadPresenter getUploadPresenter() {
        return mPresenter;
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
    public void onSuccess() {
        Toast.makeText(this, "上传成功！", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError(String errorMsg) {
        Toast.makeText(this, "上传失败:"+errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String failMsg) {
        Toast.makeText(this, "上传失败:"+failMsg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }



}
