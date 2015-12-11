package com.l000phone.themovietime.discover.Activitys;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;

import java.io.File;

public class PrevueVideoActivity extends BaseActivity {

    private VideoView videoView;
    private MediaController mediaco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevue_video);
        Intent intent=getIntent();
        String url=intent.getStringExtra("mp4Url");
        Uri uri=Uri.parse(url);
        initView();
        videoView.setVideoURI(uri);
        videoView.requestFocus();
    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.prevue_video);
        videoView.setMediaController(new MediaController(this));
    }
}
