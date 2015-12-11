package com.l000phone.themovietime;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 基类，隐藏actionbar
 * 注意：ActionBar是v7包下的，而不是app包下的，不要弄错了
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}
