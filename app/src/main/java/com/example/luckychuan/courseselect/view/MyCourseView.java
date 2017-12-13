package com.example.luckychuan.courseselect.view;

import com.example.luckychuan.courseselect.bean.MyCourseJson;

/**
 * Created by Luckychuan on 2017/12/13.
 */

public interface MyCourseView extends BaseView {

    void onSuccess(MyCourseJson.Data[] data);

}
