package com.example.luckychuan.courseselect.retrofit;

import com.example.luckychuan.courseselect.bean.CourseJson;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Luckychuan on 2017/11/21.
 */

public interface ApiService {

    @GET("")
    Observable<CourseJson[]> getSelectCourse(@Query("jb") int level, @Query("xq") String campus);

}
