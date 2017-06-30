package com.example.administrator.newsclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.administrator.newsclient.bean.NewsEntity;

public class NewsDetailActivity extends BaseActivity {
    private WebView webView;


    @Override
    protected void initData() {
        NewsEntity.ResultBean newsbean = (NewsEntity.ResultBean) getIntent().getSerializableExtra("news");

        String newsUrl = newsbean.getUrl();
        webView.loadUrl(newsUrl);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        initWebView();
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.web_view);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detail;
    }
}
