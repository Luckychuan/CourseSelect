package com.example.luckychuan.courseselect.model;

import com.example.luckychuan.courseselect.bean.BaseBeanArray;
import com.example.luckychuan.courseselect.bean.News;
import com.example.luckychuan.courseselect.bean.NewsContent;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public interface NewsModel {

    void requestNewsTitle(String userKey,int page, Callback<BaseBeanArray<News>> callback);

    void requestNewsContent(String userKey,int id, Callback<BaseBeanArray<NewsContent>> callback);


}
