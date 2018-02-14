package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.AdapterWrapper;
import com.example.luckychuan.courseselect.adapter.viewholder.ReplyRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.Reply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luckychuan on 2018/2/12.
 */

public class MessageActivity extends BaseActivity {

    private List<Reply> mList;
    private AdapterWrapper mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_content);

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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        // TODO: 2018/2/14
        //message的内容
        LinearLayout holderLayout = (LinearLayout) findViewById(R.id.holder_layout);
        View messageView = LayoutInflater.from(this).inflate(R.layout.message_content_main,holderLayout,false);
//        holderLayout.addView(messageView);
//        recyclerView.setVisibility(View.GONE);

        mList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            mList.add(new Reply());
        }
        mAdapter = new AdapterWrapper(new ReplyRecyclerAdapter(mList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayout noReplyLayout = (LinearLayout) findViewById(R.id.no_reply);
        noReplyLayout.setVisibility(View.GONE);
        mAdapter.addHeaderView(R.layout.activity_message_content,messageView);
        recyclerView.setAdapter(mAdapter);
    }
}
