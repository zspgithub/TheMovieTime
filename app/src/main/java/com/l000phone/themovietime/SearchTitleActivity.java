package com.l000phone.themovietime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.l000phone.themovietime.utils.LogUtil;

public class SearchTitleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_title);
        initView();
    }

    private void initView() {
        ImageView backImage = (ImageView) findViewById(R.id.payticket_search_fragment_imageBack);
        TextView tv_search = (TextView) findViewById(R.id.payticket_titlebar_search_tv_sousuo);
        final EditText editText = (EditText) findViewById(R.id.payticket_searchView_editText);
        ImageView erweimaImage = (ImageView) findViewById(R.id.payticket_title_erweima);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchTitleActivity.this.finish();
            }
        });
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                LogUtil.log("ss","-------"+content);
                editText.setText("");
            }
        });
        //TODO  二维码扫描
        erweimaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchTitleActivity.this,"请先登录....",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
