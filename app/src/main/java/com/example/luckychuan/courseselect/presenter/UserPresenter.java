package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.bean.TeacherJson;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.UserModel;
import com.example.luckychuan.courseselect.model.UserModelImpl;
import com.example.luckychuan.courseselect.view.LoginView;

/**
 * Created by Luckychuan on 2017/11/30.
 */

public class UserPresenter extends BasePresenter {

    private LoginView mView;
    private UserModel mModel;

    public  UserPresenter (LoginView v){
        mView = v;
        mModel = new UserModelImpl();
    }

    public void requestStudent(String account,String password){
        mView.showProgressbar();

            mModel.requestStudent(account, password, new Callback<StudentJson>() {
                @Override
                public void onNext(StudentJson bean) {
                    mView.hideProgressbar();
                    mView.onResponse(bean);
                }

                @Override
                public void onCompleted() {

                }

                @Override
                public void onFail(String errorMsg) {
                    mView.hideProgressbar();
                    mView.onError(errorMsg);
                }
            });
    }

    public void requestTeacher(String account,String password){
        mView.showProgressbar();

        mModel.requestTeacher(account, password, new Callback<TeacherJson>() {
            @Override
            public void onNext(TeacherJson bean) {
                mView.hideProgressbar();
                mView.onResponse(bean);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideProgressbar();
                mView.onError(errorMsg);
            }
        });
    }

}
