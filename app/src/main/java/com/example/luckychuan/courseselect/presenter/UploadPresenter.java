package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.BaseBean;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.UploadModel;
import com.example.luckychuan.courseselect.model.UploadModelImpl;
import com.example.luckychuan.courseselect.view.BooleanView;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Luckychuan on 2018/2/7.
 */

public class UploadPresenter extends BasePresenter {

    private BooleanView mView;
    private UploadModel mModel;

    public UploadPresenter(BooleanView view) {
        mView = view;
        mModel = new UploadModelImpl();
    }

    public void uploadNotification(String userKey, String courseId, String title, String content) {
        mView.showProgressbar();
        mModel.uploadNotification(userKey, courseId, title, content, new Callback<BaseBean<Integer>>() {
            @Override
            public void onNext(BaseBean<Integer> bean) {
                mView.hideProgressbar();
                if (bean.isSuccess()) {
                    if (bean.getData() > 0) {
                        mView.onSuccess();
                    } else {
                        mView.onFail(bean.getError());
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

    public void uploadDebate(String userKey, String courseId, String content) {
        mView.showProgressbar();
        mModel.uploadDebate(userKey, courseId, content, new Callback<BaseBean<Integer>>() {
            @Override
            public void onNext(BaseBean<Integer> bean) {
                mView.hideProgressbar();
                if (bean.isSuccess()) {
                    if (bean.getData() > 0) {
                        mView.onSuccess();
                    } else {
                        mView.onFail(bean.getError());
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

    public void uploadNotificationReply(String userKey, int replyId, String content) {
        mView.showProgressbar();
        mModel.uploadNotificationReply(userKey, replyId, content, new Callback<BaseBean<Integer>>() {
            @Override
            public void onNext(BaseBean<Integer> bean) {
                mView.hideProgressbar();
                if (bean.isSuccess()) {
                    if (bean.getData() > 0) {
                        mView.onSuccess();
                    } else {
                        mView.onFail(bean.getError());
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

    public void uploadDebateReply(String userKey, int replyId, String content) {
        mView.showProgressbar();
        mModel.uploadDebateReply(userKey, replyId, content, new Callback<BaseBean<Integer>>() {
            @Override
            public void onNext(BaseBean<Integer> bean) {
                mView.hideProgressbar();
                if (bean.isSuccess()) {
                    if (bean.getData() > 0) {
                        mView.onSuccess();
                    } else {
                        mView.onFail(bean.getError());
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

    public void uploadAttendanceCheck(String userKey, String courseId, RequestBody requestBody, String date) {
        mView.showProgressbar();
        mModel.uploadAttendanceCheck(userKey, courseId, requestBody, date, new Callback<BaseBean<Boolean>>() {
            @Override
            public void onNext(BaseBean<Boolean> bean) {
                mView.hideProgressbar();
                if (bean.isSuccess()) {
                    if (bean.getData()) {
                        mView.onSuccess();
                    } else {
                        mView.onFail(bean.getError());
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
