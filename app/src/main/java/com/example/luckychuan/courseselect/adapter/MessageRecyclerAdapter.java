package com.example.luckychuan.courseselect.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.courseselect.bean.ItemBean;
import com.example.luckychuan.courseselect.ui.WriteActivity;

import java.util.List;

/**
 * Created by Luckychuan on 2018/2/7.
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CONTENT = 1;
    private List<ItemBean> mList;
    private int mFunctionType;

    public MessageRecyclerAdapter(List<ItemBean> list,int type) {
        mList = list;
        mFunctionType = type;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_new_message, parent, false));
        }else if(viewType == TYPE_CONTENT){
            return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_message, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (mList.get(position).type == TYPE_CONTENT) {
            ((MessageViewHolder) holder).bindViewHolder((Message) (mList.get(position).bean));
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


    private class HeaderViewHolder extends BaseViewHolder {

        private LinearLayout itemLayout;

        HeaderViewHolder(final View itemView) {
            super(itemView);

            itemLayout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context= itemView.getContext();
                    Intent intent = new Intent(context, WriteActivity.class);
                    intent.putExtra("type",mFunctionType);
                    context.startActivity(intent);
                }
            });


        }

        @Override
        public void bindViewHolder(Object bean) {

        }
    }

    private class MessageViewHolder extends BaseViewHolder<Message> {

        private TextView title;
        private LinearLayout itemLayout;
        private TextView name;
        private TextView account;
        private TextView content;
        private TextView size;
        private TextView time;

        MessageViewHolder(final View itemView) {
            super(itemView);
//            title = (TextView) itemView.findViewById(R.id.news_title);
//            time = (TextView) itemView.findViewById(R.id.time);
//            itemLayout = (LinearLayout) itemView.findViewById(R.id.item_layout);

//            itemLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ItemBean lastItem = (ItemBean) mList
//                            .get(getLayoutPosition());
//
//                    if (lastItem.type == NewsRecyclerAdapter.TYPE_NEWS) {
//                        News news = (News) lastItem.bean;
//                        Intent intent = new Intent(itemView.getContext(), NewsContentActivity.class);
//                        intent.putExtra("id", news.getId());
//                        itemView.getContext().startActivity(intent);
//                    }
//                }
//            });

        }

        @Override
        public void bindViewHolder(Message message) {
//            title.setText(news.getTitle());
//            time.setText(news.getTime());


        }
    }

}
