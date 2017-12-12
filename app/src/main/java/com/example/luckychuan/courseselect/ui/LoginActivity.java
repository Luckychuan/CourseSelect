package com.example.luckychuan.courseselect.ui;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;
import com.example.luckychuan.courseselect.presenter.UserPresenter;
import com.example.luckychuan.courseselect.view.LoginView;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final String TAG = "LoginActivity";

    //标识用户是教师还是学生
    private static int mUser;
    public static final int NULL = 0;
    public static final int STUDENT = 1;
    public static final int TEACHER = 2;
    private static StudentJson.Data mStudent;
    private static TeacherJson.Data mTeacher;

    private UserPresenter mPresenter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //当用户退出账号重新登陆时
        Intent intent = getIntent();
        if(intent != null){
            if(intent.getBooleanExtra("logout",false)){
                mUser = NULL;
                saveUser();
                initView();
                return;
            }
        }

        //判断是否要登陆
        SharedPreferences sp = getSharedPreferences("user_sp", MODE_PRIVATE);
        mUser = sp.getInt("user", NULL);
        Log.d("user", "user" + mUser);
        if (mUser == NULL) {
            //第一次打开时，显示登陆界面
            initView();
        }else if(mUser == STUDENT){
            initStudent();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else if(mUser == TEACHER){
            initTeacher();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }


    private void initView() {
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

    public void doLogin(String account, String password) {
        if (mPresenter == null) {
            mPresenter = new UserPresenter(this);
        }
        mPresenter.attach(this);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.user_radioGroup);
        int id = radioGroup.getCheckedRadioButtonId();
        if (id == R.id.teacher) {
            mPresenter.requestTeacher(account, password);
        } else {
            mPresenter.requestStudent(account, password);
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
    public void onResponse(StudentJson.Data student) {
        Log.d("student", "onResponse: "+student.toString());
        mUser = STUDENT;
        saveUser();
        mStudent = student;
        saveStudent();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onResponse(TeacherJson.Data teacher) {
        mUser = TEACHER;
        saveUser();
        mTeacher = teacher;
        saveTeacher();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFail(String failMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登陆失败");
        builder.setMessage(failMsg);
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void initStudent(){
        SharedPreferences ssp = getSharedPreferences("student_sp",MODE_PRIVATE);
        mStudent = new StudentJson.Data();
        mStudent.setUserKey(ssp.getString("userKey",""));
        mStudent.setId(ssp.getString("id",""));
        mStudent.setName(ssp.getString("name",""));
        mStudent.setSex(ssp.getString("sex",""));
        mStudent.setAcademy(ssp.getString("academy",""));
        mStudent.setMajor(ssp.getString("major",""));
        mStudent.setGrade(ssp.getString("grade",""));
        mStudent.setStudentClass(ssp.getString("student_class",""));
        mStudent.setPassword(ssp.getString("password",""));
    }

    private void initTeacher(){
        SharedPreferences tsp = getSharedPreferences("teacher_sp",MODE_PRIVATE);
        mTeacher = new TeacherJson.Data();
        mTeacher.setUserKey(tsp.getString("userKey",""));
        mTeacher.setId(tsp.getString("id",""));
        mTeacher.setName(tsp.getString("name",""));
        mTeacher.setXingBie(tsp.getString("xingBie",""));
        mTeacher.setChuShengRiQi(tsp.getString("chuShengRiQi",""));
        mTeacher.setBuMen(tsp.getString("buMen",""));
        mTeacher.setKeShi(tsp.getString("keShi",""));
        mTeacher.setZhiCheng(tsp.getString("zhiCheng",""));
        mTeacher.setXueLi(tsp.getString("xueLi",""));
        mTeacher.setPassword(tsp.getString("password",""));
        mTeacher.setJianLi(tsp.getString("jianLi",""));
    }

    private void saveTeacher() {
        SharedPreferences.Editor sp = getSharedPreferences("teacher_sp", MODE_PRIVATE).edit();
        sp.putString("userKey", mTeacher.getUserKey());
        sp.putString("id", mTeacher.getId());
        sp.putString("name",mTeacher.getName());
        sp.putString("xingBie", mTeacher.getXingBie());
        sp.putString("chuShengRiQi", mTeacher.getChuShengRiQi());
        sp.putString("buMen", mTeacher.getBuMen());
        sp.putString("keShi", mTeacher.getKeShi());
        sp.putString("zhiCheng", mTeacher.getZhiCheng());
        sp.putString("xueLi", mTeacher.getXueLi());
        sp.putString("password", mTeacher.getPassword());
        sp.putString("jianLi", mTeacher.getJianLi());
        sp.apply();
    }

    private void saveStudent() {
        SharedPreferences.Editor sp = getSharedPreferences("student_sp", MODE_PRIVATE).edit();
        sp.putString("userKey", mStudent.getUserKey());
        sp.putString("id", mStudent.getId());
        sp.putString("name", mStudent.getName());
        sp.putString("sex",mStudent.getSex());
        sp.putString("academy", mStudent.getAcademy());
        sp.putString("major", mStudent.getMajor());
        sp.putString("grade", mStudent.getGrade());
        sp.putString("student_class", mStudent.getStudentClass());
        sp.putString("password", mStudent.getPassword());
        sp.apply();
    }

    private void saveUser() {
        SharedPreferences.Editor sp = getSharedPreferences("user_sp", MODE_PRIVATE).edit();
        sp.putInt("user", mUser);
        sp.apply();
    }

    public static StudentJson.Data getStudent() {
        return mStudent;
    }

    public static TeacherJson.Data getTeacher() {
        return mTeacher;
    }

    public static int getUser() {
        return mUser;
    }


}
