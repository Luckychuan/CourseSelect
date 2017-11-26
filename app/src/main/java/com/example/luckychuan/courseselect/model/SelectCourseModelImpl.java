package com.example.luckychuan.courseselect.model;

import android.util.Log;

import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;
import com.example.luckychuan.courseselect.bean.CourseJson;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public class SelectCourseModelImpl implements SelectCourseModel {

    @Override
    public void requestData(int[] levels, final String campus, final Callback<CourseJson[]> callback) {
        //将int[]转成Integer[]
        Integer[] integers = new Integer[levels.length];
        for (int i = 0; i < levels.length; i++) {
            integers[i] = levels[i];
        }

        //循环4个level请求http
        Observable.from(integers)
                .flatMap(new Func1<Integer, Observable<CourseJson[]>>() {
                    @Override
                    public Observable<CourseJson[]> call(Integer integer) {
                        Log.d("rx_json", "level:"+integer+" campus:"+campus);
                        return CustomRetrofit.getService().getSelectCourse(integer, campus);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<CourseJson[]>() {
                    @Override
                    public void onCompleted() {
                        Log.d("rx_json", "onCompleted: ");
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail(e.toString());
                    }

                    @Override
                    public void onNext(CourseJson[] courseJson) {
                        Log.d("rx_json", "onNext: ");
                        callback.onNext(courseJson);
                    }
                });

    }
}
