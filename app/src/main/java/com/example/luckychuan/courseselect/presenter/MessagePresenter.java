package com.example.luckychuan.courseselect.presenter;

import android.app.Notification;

import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Message;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.MessageModel;
import com.example.luckychuan.courseselect.model.MessageModelImpl;
import com.example.luckychuan.courseselect.view.MessageView;

/**
 * Created by Luckychuan on 2018/2/8.
 */

public class MessagePresenter extends BasePresenter {

    private MessageView mView;
    private MessageModel mModel;

    public MessagePresenter(MessageView view) {
        mView = view;
        mModel = new MessageModelImpl();
    }

    public void getNotification(String userKey, String courseId) {
        mView.showProgressbar();
        mModel.getNotification(userKey, courseId, new Callback<BaseBeanArray<Message>>() {
            @Override
            public void onNext(BaseBeanArray<Message> bean) {
                mView.hideProgressbar();
                if (bean.isSuccess()) {
                    mView.onNotificationSuccess(bean.getDatas());
                    return;
                }
                mView.onFail(bean.getError());

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

    public void getDebate(String userKey, String courseId, int page) {
        mView.showProgressbar();
        mModel.getDebate(userKey, courseId, page, new Callback<BaseBeanArray<Message>>() {
            @Override
            public void onNext(BaseBeanArray<Message> bean) {
                mView.hideProgressbar();
                if (bean.isSuccess()) {
                    mView.onDebateSuccess(bean.getDatas());
                    return;
                }
                mView.onFail(bean.getError());

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
