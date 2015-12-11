package com.l000phone.themovietime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.l000phone.themovietime.utils.LogUtil;

/**
 * 商品详情类，直接WebView加载
 *
 * 传过来的参数是 Intent.putExtra("url",url)
 */
public class WebViewActivity extends BaseActivity {

    private WebView good_detail_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);

        good_detail_webview = (WebView) findViewById(R.id.good_detail_webview);

        WebSettings webSettings = good_detail_webview.getSettings();
        webSettings.setDomStorageEnabled(true);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        LogUtil.log("first",url+"网址");

        good_detail_webview.loadUrl(url);

    }
}
