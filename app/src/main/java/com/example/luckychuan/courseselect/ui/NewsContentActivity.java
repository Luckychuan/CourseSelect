package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.News;
import com.example.luckychuan.courseselect.bean.NewsContent;
import com.example.luckychuan.courseselect.presenter.NewsPresenter;
import com.example.luckychuan.courseselect.view.NewsView;

/**
 * Created by Luckychuan on 2018/1/31.
 */

public class NewsContentActivity extends BaseActivity implements NewsView {

    private Toolbar mToolbar;
    private NewsPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content);

        //初始化toolbar
        mToolbar = (Toolbar) findViewById(R.id.course_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mPresenter = new NewsPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestNewsContent(LoginActivity.getUserKey(), getIntent().getStringExtra("id"));

    }


    @Override
    public void onResponse(News[] newsArray) {

    }

    @Override
    public void onResponse(NewsContent[] newsContent) {
        mToolbar.setTitle(newsContent[0].getTitle());

        //初始化WebView
        WebView webView = (WebView)  findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        webView.loadData(newsContent[0].getContent(), "text/html; charset=UTF-8", null);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }


}
