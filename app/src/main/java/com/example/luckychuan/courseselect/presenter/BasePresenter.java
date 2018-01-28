package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.view.BaseView;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public class BasePresenter {

    private BaseView mView;

    //// TODO: 2018/1/24  
    public void attach(BaseView view){
        mView = view;
    }

    public void detach(){
        mView = null;
    }

}
