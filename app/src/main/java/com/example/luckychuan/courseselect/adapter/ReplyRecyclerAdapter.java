package com.example.luckychuan.courseselect.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.viewholder.BaseViewHolder;
import com.example.luckychuan.courseselect.bean.Reply;

import java.util.List;

/**
 * Created by Luckychuan on 2018/2/14.
 */

public class ReplyRecyclerAdapter extends RecyclerView.Adapter<ReplyRecyclerAdapter.ReplyViewHolder> {

    private List<Reply> mList;

    public ReplyRecyclerAdapter(List<Reply> list){
        mList = list;
    }

    @Override
    public ReplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReplyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_reply,parent,false));
    }

    @Override
    public void onBindViewHolder(ReplyViewHolder holder, int position) {
        holder.bindViewHolder(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ReplyViewHolder extends BaseViewHolder<Reply>{

        private TextView author_name;
        private TextView author_id;
        private TextView reply_to_id;
        private TextView reply_to_name;
        private TextView content;
        private TextView time;


        ReplyViewHolder(View itemView) {
            super(itemView);
            author_name = itemView.findViewById(R.id.reply_user_name);
            author_id = itemView.findViewById(R.id.reply_user_account);
            reply_to_name = itemView.findViewById(R.id.reply_to_user_name);
            reply_to_id = itemView.findViewById(R.id.reply_to_user_account);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
        }


        public void bindViewHolder(Reply reply){
            author_name.setText(reply.getAuthorName());
            author_id.setText(reply.getAuthorId());
            reply_to_name.setText(reply.getReplyToName());
            reply_to_id.setText(reply.getReplyToId());
            content.setText(reply.getContent());
            time.setText(reply.getTime());
        }

    }

}
