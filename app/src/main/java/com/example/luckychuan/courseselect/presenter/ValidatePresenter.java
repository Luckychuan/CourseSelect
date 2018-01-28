package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.BaseBean;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.ValidateAccountModel;
import com.example.luckychuan.courseselect.model.ValidateModelImpl;
import com.example.luckychuan.courseselect.view.ValidateView;

/**
 * Created by Luckychuan on 2018/1/24.
 */

public class ValidatePresenter extends BasePresenter{

    private ValidateView mView;
    private ValidateAccountModel mModel;

    public  ValidatePresenter (ValidateView v){
        mView = v;
        mModel = new ValidateModelImpl();
    }

    public void requestValidate(String userKey){
      mView.showProgressbar();
        mModel.requestValidate(userKey, new Callback<BaseBean<Boolean>>() {
            @Override
            public void onNext(BaseBean<Boolean> bean) {
                mView.hideProgressbar();
                if(bean.getData()){
                    mView.onResponse(true);
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

    public void attach() {
        super.attach(mView);
    }

}
