package com.example.luckychuan.courseselect.presenter;

import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.model.Callback;
import com.example.luckychuan.courseselect.model.SelectCourseModel;
import com.example.luckychuan.courseselect.model.SelectCourseModelImpl;
import com.example.luckychuan.courseselect.view.SelectCourseView;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public class SelectCoursePresenter extends BasePresenter {

    private SelectCourseView mView;
    private SelectCourseModel mModel;

    public SelectCoursePresenter(SelectCourseView view) {
        mView = view;
        mModel = new SelectCourseModelImpl();
    }

    public void requestData(int[] levels, String campus) {
        mView.showProgressbar();
        mModel.requestData(levels, campus, new Callback<CourseJson[]>() {
            @Override
            public void onNext(CourseJson[] bean) {
                mView.addData(bean);
            }

            @Override
            public void onCompleted() {
                mView.hideProgressbar();
                mView.loadSelectCourseUI();
            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideProgressbar();
                mView.onError(errorMsg);
            }
        });
    }

}
