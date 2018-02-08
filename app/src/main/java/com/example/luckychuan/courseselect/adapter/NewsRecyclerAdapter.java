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

public class NewsRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int TYPE_NEWS = 0;
    public static final int TYPE_LOADING = 1;
    public static final int TYPE_NO_MORE = 2;
    private List<ItemBean> mList;

    public NewsRecyclerAdapter(List<ItemBean> list) {
        mList = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_NEWS) {
            viewHolder = new NewsViewHolder(inflater.inflate(R.layout.recycler_news, parent, false));
        } else if (viewType == TYPE_LOADING) {
            viewHolder = new FooterViewHolder(inflater.inflate(R.layout.recycler_loading, parent, false));
        } else if (viewType == TYPE_NO_MORE) {
            viewHolder = new FooterViewHolder(inflater.inflate(R.layout.recycler_no_more, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (mList.get(position).type == TYPE_NEWS) {
            ((NewsViewHolder) holder).bindViewHolder((News) (mList.get(position).bean));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    private class FooterViewHolder extends BaseViewHolder {
        FooterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindViewHolder(Object bean) {

        }
    }


    private class NewsViewHolder extends BaseViewHolder<News> {

        private TextView title;
        private TextView time;

        NewsViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title);
            time = (TextView) itemView.findViewById(R.id.time);
            LinearLayout item = (LinearLayout)itemView.findViewById(R.id.item_layout);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ItemBean lastItem = (ItemBean) mList
                            .get(getLayoutPosition());

                    if (lastItem.type == NewsRecyclerAdapter.TYPE_NEWS) {
                        News news = (News) lastItem.bean;
                        Intent intent = new Intent(itemView.getContext(), NewsContentActivity.class);
                        intent.putExtra("id",news.getId());
                        itemView.getContext().startActivity(intent);
                    }
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



