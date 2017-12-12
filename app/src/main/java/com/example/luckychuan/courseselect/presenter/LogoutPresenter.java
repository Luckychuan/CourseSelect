package com.example.luckychuan.courseselect.presenter;

import android.view.View;

import com.example.luckychuan.courseselect.bean.LogoutJson;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.LogoutModel;
import com.example.luckychuan.courseselect.model.LogoutModelImpl;
import com.example.luckychuan.courseselect.view.LogoutView;

/**
 * Created by Luckychuan on 2017/12/12.
 */

public class LogoutPresenter extends BasePresenter {

    private LogoutView mView;
    private LogoutModel mModel;

    public  LogoutPresenter (LogoutView v){
        mView = v;
        mModel = new LogoutModelImpl();
    }

    public void requestLogout(String userKey){
        mModel.requestLogout(userKey, new Callback<LogoutJson>() {
            @Override
            public void onNext(LogoutJson bean) {
                mView.onResponse(bean);
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
