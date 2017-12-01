package com.example.luckychuan.courseselect.ui;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;
import com.example.luckychuan.courseselect.presenter.UserPresenter;
import com.example.luckychuan.courseselect.view.LoginView;

import java.io.Serializable;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final String TAG = "LoginActivity";
    private UserPresenter mPresenter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        final TextInputLayout account = (TextInputLayout) findViewById(R.id.editLayout_account);
        final TextInputLayout password = (TextInputLayout) findViewById(R.id.editLayout_password);
        EditText ea = (EditText) findViewById(R.id.edit_account);
        EditText ep = (EditText) findViewById(R.id.edit_password);

        ea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                account.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Button button = (Button) findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountString = account.getEditText().getText().toString();
                String passwordString = password.getEditText().getText().toString();

                if (!validateAccount(accountString)) {
                    account.setError("学号不能为空");
                } else if (!validatePassword(passwordString)) {
                    password.setError("密码不能为空");
                } else {
                    account.setErrorEnabled(false);
                    password.setErrorEnabled(false);
                    doLogin(accountString, passwordString);
                }
            }
        });

    }


    public boolean validateAccount(String account) {
        if (account.length() == 0) {
            return false;
        }

        return true;
    }

    public boolean validatePassword(String password) {
        if (password.length() == 0) {
            return false;
        }

        return true;
    }

    @Override
    public void showProgressbar() {
        mProgressDialog = ProgressDialog.show(this, "提示", "登陆中");
    }

    @Override
    public void hideProgressbar() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();

    }

    public void showFailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登陆失败");
        builder.setMessage("用户名或密码错误");
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }


    public void doLogin(String account, String password) {
        if (mPresenter == null) {
            mPresenter = new UserPresenter(this);
        }
        mPresenter.attach(this);
        mPresenter.requestTeacher(account, password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    @Override
    public void onResponse(StudentJson student) {
        if (!student.isSuccess()) {
            showFailDialog();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("student", (Serializable) student.getData());
        Log.d(TAG, "onResponse: "+student.toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void onResponse(TeacherJson teacher) {
        if (!teacher.isSuccess()) {
            showFailDialog();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("teacher", (Serializable) teacher.getData());
        Log.d(TAG, "onResponse: "+teacher.toString());
        startActivity(intent);
        finish();
    }


}
