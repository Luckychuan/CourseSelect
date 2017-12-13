package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.MyCourseJson;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.MyCourseModel;
import com.example.luckychuan.courseselect.model.MyCourseModelImpl;
import com.example.luckychuan.courseselect.view.MyCourseView;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public class MyCoursePresenter extends BasePresenter {

    private MyCourseModel mModel;
    private MyCourseView mView;

    public MyCoursePresenter(MyCourseView view){
        mModel = new MyCourseModelImpl();
        mView = view;
    }

    public void requestMyCourse(String userKey){
        mModel.requestMyCourse(userKey, new Callback<MyCourseJson>() {
            @Override
            public void onNext(MyCourseJson bean) {
                if(bean.isSuccess()){
                    mView.onSuccess(bean.getData());
                    return;
                }
                mView.onFail(bean.getError());

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onFail(String errorMsg) {
                mView.onError(errorMsg);
            }
        });
    }

}
