package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.AdapterWrapper;
import com.example.luckychuan.courseselect.adapter.ReplyRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.Message;
import com.example.luckychuan.courseselect.bean.Reply;
import com.example.luckychuan.courseselect.presenter.ReplyPresenter;
import com.example.luckychuan.courseselect.view.ReplyView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Luckychuan on 2018/2/12.
 */

public class MessageActivity extends BaseActivity implements ReplyView {

    private ReplyPresenter mPresenter;
    private List<Reply> mList = new ArrayList<>();
    private AdapterWrapper mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayout mHolderLayout;
    private View mMessageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_content);

        Message message = (Message) getIntent().getSerializableExtra("message");

        //初始化toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("详情");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mHolderLayout = (LinearLayout) findViewById(R.id.holder_layout);
        mMessageView = LayoutInflater.from(this).inflate(R.layout.message_content_main, mHolderLayout, false);
        TextView name = (TextView) mMessageView.findViewById(R.id.name);
        TextView account = (TextView) mMessageView.findViewById(R.id.account);
        TextView title = (TextView) mMessageView.findViewById(R.id.title);
        TextView content = (TextView) mMessageView.findViewById(R.id.content);
        TextView time = (TextView) mMessageView.findViewById(R.id.time);
        name.setText(message.getUserName());
        account.setText(message.getAccount());
        if(message.getTitle() != null){
            title.setText(message.getTitle());
        }else{
            title.setVisibility(View.GONE);
        }
        content.setText(message.getContent());
        time.setText(message.getTime());


        mPresenter = new ReplyPresenter(this);
        mPresenter.attach(this);
        mPresenter.getReplies(LoginActivity.getUserKey(),message.getId());


    }

    @Override
    public void onSuccess(Reply[] replies) {
        if (replies.length == 0) {
            showNoReplyView();
            return;
        }

        if (mAdapter == null) {
            mAdapter = new AdapterWrapper(new ReplyRecyclerAdapter(mList));
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            LinearLayout noReplyLayout = (LinearLayout) findViewById(R.id.no_reply);
            noReplyLayout.setVisibility(View.GONE);
            mAdapter.addHeaderView(R.layout.activity_message_content, mMessageView);
            mRecyclerView.setAdapter(mAdapter);
        }
        Collections.addAll(mList, replies);
        mAdapter.notifyDataSetChanged();


    }

    private void showNoReplyView() {
        mHolderLayout.addView(mMessageView);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        showNoReplyView();
    }

    @Override
    public void onFail(String failMsg) {
        super.onFail(failMsg);
        showNoReplyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
