package com.example.luckychuan.courseselect.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.courseselect.adapter.viewholder.NewsViewHolder;
import com.example.luckychuan.courseselect.adapter.viewholder.ViewHolder;
import com.example.luckychuan.courseselect.bean.News;

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
            viewHolder = new ViewHolder(inflater.inflate(R.layout.recycler_loading, parent, false));
        } else if (viewType == TYPE_NO_MORE) {
            viewHolder = new ViewHolder(inflater.inflate(R.layout.recycler_no_more, parent, false));
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

    public static class ItemBean {
        public int type;
        public Object bean;

        public ItemBean(int type, Object bean) {
            this.type = type;
            this.bean = bean;
        }
    }

}



