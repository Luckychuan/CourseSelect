package com.example.luckychuan.courseselect.view;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public interface BaseView {

    void showProgressbar();
    void hideProgressbar();
    void onError(String errorMsg);
    void onFail(String failMsg);

}
