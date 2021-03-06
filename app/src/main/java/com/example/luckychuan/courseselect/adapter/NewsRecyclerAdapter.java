package com.example.luckychuan.courseselect.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.courseselect.bean.ItemBean;
import com.example.luckychuan.courseselect.bean.News;
import com.example.luckychuan.courseselect.ui.NewsContentActivity;

import java.util.List;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {

    private List<News> mList;

    public NewsRecyclerAdapter(List<News> list) {
        mList = list;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
           LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NewsViewHolder(inflater.inflate(R.layout.recycler_news, parent, false));

    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bindViewHolder((News) mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

     class NewsViewHolder extends BaseViewHolder<News> {

        private TextView title;
        private TextView time;

        NewsViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title);
            time = (TextView) itemView.findViewById(R.id.time);
            LinearLayout item = (LinearLayout) itemView.findViewById(R.id.item_layout);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = (News) mList.get(getLayoutPosition());
                    Intent intent = new Intent(itemView.getContext(), NewsContentActivity.class);
                    intent.putExtra("id", news.getId());
                    itemView.getContext().startActivity(intent);
                }
            });

        }

        @Override
        public void bindViewHolder(News news) {
            title.setText(news.getTitle());
            time.setText(news.getTime());


        }
    }


}



