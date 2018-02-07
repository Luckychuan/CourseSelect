package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.BaseBean;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.WriteMessageModel;
import com.example.luckychuan.courseselect.model.WriteMessageModelImpl;
import com.example.luckychuan.courseselect.view.BooleanView;

/**
 * Created by Luckychuan on 2018/2/7.
 */

public class WriteMessagePresenter extends BasePresenter {

    private BooleanView mView;
    private WriteMessageModel mModel;

    public WriteMessagePresenter(BooleanView view){
        mView = view;
        mModel = new WriteMessageModelImpl();
    }

    public void uploadNotification(String userKey, String courseId, String title, String content){
        mView.showProgressbar();
        mModel.uploadNotification(userKey, courseId, title, content, new Callback<BaseBean<Integer>>() {
            @Override
            public void onNext(BaseBean<Integer> bean) {
                mView.hideProgressbar();
                if(bean.isSuccess()){
                    mView.onSuccess(true);
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
            }
        });
    }

}
