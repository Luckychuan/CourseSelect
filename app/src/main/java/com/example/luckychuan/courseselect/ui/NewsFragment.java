package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.courseselect.MoreRecyclerLayout;
import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class NewsFragment extends BaseFragment {

    private MoreRecyclerLayout mLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayout = (MoreRecyclerLayout) view.findViewById(R.id.more_layout);
        List<News> list = new ArrayList<>();

        //// TODO: 2018/1/31
        for (int i = 0; i < 15; i++) {
            News news = new News();
            news.setTitle("关于2011-2012-1学期体育课第二轮选课后停开课程信息");
            news.setTime("2011-8-26 20:50:44");
            list.add(news);
        }
        mLayout.addData(list);

    }
}
