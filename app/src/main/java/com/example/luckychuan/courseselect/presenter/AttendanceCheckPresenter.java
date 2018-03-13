package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.AttendanceCheck;
import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Date;
import com.example.luckychuan.courseselect.model.AttendanceCheckModel;
import com.example.luckychuan.courseselect.model.AttendanceCheckModelImpl;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.view.AttendanceCheckView;

/**
 * Created by Luckychuan on 2018/3/10.
 */

public class AttendanceCheckPresenter extends BasePresenter {

    private AttendanceCheckModel mModel;
    private AttendanceCheckView mView;

    public AttendanceCheckPresenter(AttendanceCheckView view) {
        mModel = new AttendanceCheckModelImpl();
        mView = view;
    }

    public void getAttendanceCheckDates(String userKey, String courseId) {
        mModel.getAttendanceCheckDates(userKey, courseId, new Callback<BaseBeanArray<Date>>() {
            @Override
            public void onNext(BaseBeanArray<Date> bean) {
                if (bean.isSuccess()) {
                    mView.onSuccess(bean.getDatas());
                } else {
                    mView.onFail(bean.getError());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String errorMsg) {
                mView.onError(errorMsg);
            }
        });
    }

    public void getAttendanceChecks(String userKey, String courseId, final String date) {
        mView.showProgressbar();
        mModel.getAttendanceChecks(userKey, courseId, date, new Callback<BaseBeanArray<AttendanceCheck>>() {
            @Override
            public void onNext(BaseBeanArray<AttendanceCheck> bean) {
                mView.hideProgressbar();
                if (bean.isSuccess()) {
                    if(date != null){
                        mView.onSuccess(bean.getDatas());
                    }else {
                        mView.onStudentInfoSuccess(bean.getDatas());
                    }
                } else {
                    mView.onFail(bean.getError());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(String errorMsg) {
                mView.hideProgressbar();
                mView.onError(errorMsg);
            }
        });

    }
}
