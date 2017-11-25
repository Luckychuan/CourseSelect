package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.CourseJson;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public class SelectCourseModelImpl implements SelectCourseModel {

    @Override
    public void requestData(int level, String campus, final Callback<CourseJson[]> callback) {
        CustomRetrofit.getService().
                getSelectCourse(level, campus)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<CourseJson[]>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e.toString());
                    }

                    @Override
                    public void onNext(CourseJson[] courseJson) {
                        callback.onSuccess(courseJson);
                    }
                });
    }
}
