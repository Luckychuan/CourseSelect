package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.LogoutJson;

/**
 * Created by Luckychuan on 2017/12/12.
 */

public interface LogoutModel {
    void requestLogout(String userKey,Callback<LogoutJson> callback);
}
