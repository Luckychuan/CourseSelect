package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.Reply;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.ReplyModel;
import com.example.luckychuan.courseselect.model.ReplyModelImpl;
import com.example.luckychuan.courseselect.view.ReplyView;

/**
 * Created by Luckychuan on 2018/2/14.
 */

public class ReplyPresenter extends BasePresenter {

    private ReplyModel mModel;
    private ReplyView mView;

    public ReplyPresenter(ReplyView view){
        mView = view;
        mModel = new ReplyModelImpl();
    }

    public void getReplies(String userKey, int replyId){
        mView.showProgressbar();
        mModel.getReplies(userKey, replyId, new Callback<BaseBeanArray<Reply>>() {
            @Override
            public void onNext(BaseBeanArray<Reply> bean) {
                mView.hideProgressbar();
                if(bean.isSuccess()){
                    mView.onSuccess(bean.getDatas());
                }else{
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

    public void getDebateReplies(String userKey, int replyId){
        mView.showProgressbar();
        mModel.getDebateReplies(userKey, replyId, new Callback<BaseBeanArray<Reply>>() {
            @Override
            public void onNext(BaseBeanArray<Reply> bean) {
                mView.hideProgressbar();
                if(bean.isSuccess()){
                    mView.onSuccess(bean.getDatas());
                }else{
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
