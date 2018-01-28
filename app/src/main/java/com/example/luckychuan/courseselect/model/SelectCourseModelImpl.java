package com.example.luckychuan.courseselect.model;

import android.util.Log;

import com.example.luckychuan.courseselect.bean.CourseInfoJson;
import com.example.luckychuan.courseselect.bean.CourseJson;
import com.example.luckychuan.courseselect.bean.SelectCourseJson;
import com.example.luckychuan.courseselect.bean.StudentJson;
import com.example.luckychuan.courseselect.retrofit.CustomRetrofit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public class SelectCourseModelImpl implements SelectCourseModel {

//    @Override
//    public void requestData(int[] levels, final String userKey , final String campus, final Callback<CourseJson[]> callback) {
//        //将int[]转成Integer[]
//        Integer[] integers = new Integer[levels.length];
//        for (int i = 0; i < levels.length; i++) {
//            integers[i] = levels[i];
//        }
//
//        //循环4个level请求http
//        Observable.from(integers)
//                .flatMap(new Func1<Integer, Observable<SelectCourseJson>>() {
//                    @Override
//                    public Observable<SelectCourseJson> call(Integer integer) {
//                        Log.d("rx_json", "level:"+integer+" campus:"+campus);
//                        return CustomRetrofit.getService().getSelectCourse(integer,userKey ,campus);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<SelectCourseJson>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d("rx_json", "onCompleted: ");
//                        callback.onCompleted();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onError(e.toString());
//                    }
//
//                    @Override
//                    public void onNext(SelectCourseJson json) {
//                        if(json.isSuccess()){
//                            callback.onNext(json.getData());
//                        }
//                    }
//                });
//
//    }

    @Override
    public void requestCourseInfo(String userKey, String id, final Callback<CourseInfoJson> callback) {
        CustomRetrofit.getService()
                .getCourseInfo(userKey, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CourseInfoJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("CourseInfo", "onError: "+e.toString());
                        callback.onError(e.toString());
                    }

                    @Override
                    public void onNext(CourseInfoJson courseInfoJson) {
                        callback.onNext(courseInfoJson);
                    }
                });
    }
}
