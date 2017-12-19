package com.example.luckychuan.courseselect.view;

/**
 * Created by Luckychuan on 2017/12/17.
 */

public interface SelectTaskView  {

    void showCountDown(int countDown);
    void showRequestMsg(String requestMsg);
    void showErrorMsg(String errorMsg);
    void onFail(String failMsg);
    void onSuccess(String successMsg);
    void showLog(String log);
    void showTaskEndMsg(String endMsg);
    void showNextMsg(String nextMsg);

}
