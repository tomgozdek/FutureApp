package com.tomekgozdek.futureapp.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tomekgozdek.futureapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomekG on 2017-04-02.
 */

public class WebViewActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "extra_url";

    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        ButterKnife.bind(this);

        if(getIntent().getExtras() != null){
            String url = getIntent().getStringExtra(EXTRA_URL);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new CustomWebClient());
            webView.loadUrl(url);
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
    //Override onBackPressed to allow navigation web page history by pressing back key.
        if(webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class CustomWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
