package com.example.luckychuan.courseselect.model;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public interface Callback<T> {

    void onNext(T bean);

    void onCompleted();

    void onError(String errorMsg);

}
