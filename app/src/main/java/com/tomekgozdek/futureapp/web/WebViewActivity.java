package com.tomekgozdek.futureapp.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.tomekgozdek.futureapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TomekG on 2017-04-02.
 */

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        ButterKnife.bind(this);
    }
}
