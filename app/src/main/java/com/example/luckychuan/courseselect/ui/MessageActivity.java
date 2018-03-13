package com.example.luckychuan.courseselect.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.adapter.AdapterWrapper;
import com.example.luckychuan.courseselect.adapter.ReplyRecyclerAdapter;
import com.example.luckychuan.courseselect.bean.Message;
import com.example.luckychuan.courseselect.bean.Reply;
import com.example.luckychuan.courseselect.presenter.ReplyPresenter;
import com.example.luckychuan.courseselect.presenter.UploadPresenter;
import com.example.luckychuan.courseselect.view.BooleanView;
import com.example.luckychuan.courseselect.view.ReplyView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Luckychuan on 2018/2/12.
 */

public class MessageActivity extends UploadActivity implements ReplyRecyclerAdapter.OnItemClickListener {

    private static final String TAG = "MessageActivity";
    private ReplyPresenter mPresenter;
    private List<Reply> mList = new ArrayList<>();
    private AdapterWrapper mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayout mHolderLayout;
    private View mMessageView;

    private int mType;

    //要回复的内容的id
    private int mSendId;
    private int mMessageId;

    private EditText mEditText;
    private Button mSendButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_content);

        final Message message = (Message) getIntent().getSerializableExtra("message");
        mType = getIntent().getIntExtra("type",-1);
        mMessageId = message.getId();
        mSendId = mMessageId;

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
        if (message.getTitle() != null && !message.getTitle().equals("")) {
            title.setText(message.getTitle());
        } else {
            title.setVisibility(View.GONE);
        }
        content.setText(message.getContent());
        time.setText(message.getTime());


        mPresenter = new ReplyPresenter(new ReplyView() {
            @Override
            public void onSuccess(Reply[] replies) {
                if (replies == null || replies.length == 0) {
                    showNoReplyView();
                    return;
                }

                if (mAdapter == null) {
                    mAdapter = new AdapterWrapper(new ReplyRecyclerAdapter(mList, MessageActivity.this));
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    LinearLayout noReplyLayout = (LinearLayout) findViewById(R.id.no_reply);
                    noReplyLayout.setVisibility(View.GONE);
                    mAdapter.addHeaderView(R.layout.activity_message_content, mMessageView);
                    mRecyclerView.setAdapter(mAdapter);
                }
                mRecyclerView.setVisibility(View.VISIBLE);
                mHolderLayout.removeView(mMessageView);
                mHolderLayout.setVisibility(View.GONE);
                Collections.addAll(mList, replies);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void showProgressbar() {

            }

            @Override
            public void hideProgressbar() {

            }

            @Override
            public void onError(String errorMsg) {
                MessageActivity.super.onError(errorMsg);
            }

            @Override
            public void onFail(String failMsg) {

            }
        });
        mPresenter.attach(this);

        if(mType == MessageFragment.TYPE_NOTIFICATION){
            mPresenter.getReplies(LoginActivity.getUserKey(), mMessageId);
        }else if(mType == MessageFragment.TYPE_DEBATE){
            mPresenter.getDebateReplies(LoginActivity.getUserKey(), mMessageId);
        }

        //edit初始化
        mEditText = (EditText) findViewById(R.id.reply_message_edit);
        mSendButton = (Button) findViewById(R.id.send_button);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mSendButton.setEnabled(false);
                } else {
                    mSendButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getUploadPresenter().attach(MessageActivity.this);
                String content = mEditText.getText().toString();
                if (content.trim().equals("")) {
                    Toast.makeText(MessageActivity.this, "不能输入纯空格号", Toast.LENGTH_SHORT).show();
                } else {
                    mSendButton.setEnabled(false);
                    if(mType == MessageFragment.TYPE_NOTIFICATION){
                        getUploadPresenter().uploadNotificationReply(LoginActivity.getUserKey(), mSendId, content);
                    }else if(mType == MessageFragment.TYPE_DEBATE){
                        getUploadPresenter().uploadDebateReply(LoginActivity.getUserKey(), mSendId, content);
                    }
                }
            }
        });

        Button replyButton = (Button) mMessageView.findViewById(R.id.reply_button);
        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doEdit(mMessageId, message.getUserName());
            }
        });


    }


    private void showNoReplyView() {
        LinearLayout noReplyLayout = (LinearLayout) findViewById(R.id.no_reply);
        noReplyLayout.setVisibility(View.VISIBLE);
        mHolderLayout.addView(mMessageView);
        mRecyclerView.setVisibility(View.GONE);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        mSendButton.setEnabled(true);
    }

    @Override
    public void onFail(String failMsg) {
        super.onFail(failMsg);
        mSendButton.setEnabled(true);
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "上传成功！", Toast.LENGTH_SHORT).show();
        mEditText.setText("");
        mEditText.setHint("我也来说一句...");
        //收起键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);

        mList.clear();
        if(mType == MessageFragment.TYPE_NOTIFICATION){
            mPresenter.getReplies(LoginActivity.getUserKey(), mMessageId);
        }else if(mType == MessageFragment.TYPE_DEBATE){
            mPresenter.getDebateReplies(LoginActivity.getUserKey(), mMessageId);
        }
    }

    @Override
    public void onItemClick(int position) {
        //因为加了headerView，position要减1
        Reply reply = mList.get(position - 1);
        doEdit(reply.getId(), reply.getAuthorName());

    }

    private void doEdit(int replyId, String replyName) {
        mSendId = replyId;

        //让编辑框弹出来，并显示对谁进行评论
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.requestFocus();
        //打开软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        mEditText.setHint("回复：" + replyName);
    }

}
