package com.example.luckychuan.courseselect.view;

import com.example.luckychuan.courseselect.bean.News;
import com.example.luckychuan.courseselect.bean.NewsContent;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public interface NewsView extends BaseView {

    void onResponse(News[] newsArray);

    void onResponse(NewsContent[] newsContent);

}
