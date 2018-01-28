package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.BaseBean;
import com.example.luckychuan.courseselect.bean.LogoutJson;

/**
 * Created by Luckychuan on 2018/1/24.
 */

public interface ValidateAccountModel {

    void requestValidate(String userKey,Callback<BaseBean<Boolean>> callback);


}
