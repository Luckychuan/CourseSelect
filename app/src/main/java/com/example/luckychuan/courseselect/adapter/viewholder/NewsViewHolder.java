package com.example.luckychuan.courseselect.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.News;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class NewsViewHolder extends BaseViewHolder<News> {

    private TextView title;
    private TextView time;

    public NewsViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.news_title);
        time = (TextView) itemView.findViewById(R.id.time);
    }

    @Override
    public void bindViewHolder(News news) {
        title.setText(news.getTitle());
        time.setText(news.getTime());


    }
}
