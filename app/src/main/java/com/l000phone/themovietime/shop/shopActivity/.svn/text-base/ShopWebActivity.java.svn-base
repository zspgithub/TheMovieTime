package com.l000phone.themovietime.shop.shopActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.shopDataParse.ShopBuyDataParse;
import com.l000phone.themovietime.shop.shopbuybean.GotoPage;


public class ShopWebActivity extends BaseActivity {


    private String url;
    private WebView webView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;

            switch (what){

                case 1:
                    GotoPage gotoPage = (GotoPage) msg.obj;

                    String urla = gotoPage.getUrl();

                    webView.loadUrl(urla);
                    break;



            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_web);

        initView();

        ShopBuyDataParse.viewPagerDataParse(url, handler);
    }

    private void initView() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("url");

        webView = (WebView) findViewById(R.id.webVIew);
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
