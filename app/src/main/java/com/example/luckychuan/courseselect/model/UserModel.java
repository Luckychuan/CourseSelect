package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.StudentJson;

/**
 * Created by Luckychuan on 2017/11/30.
 */

public interface UserModel {

    void requestStudent(String account, String password, Callback<StudentJson> callback);

}
