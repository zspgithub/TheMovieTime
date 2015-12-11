package com.l000phone.themovietime;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 当没有网络连接时跳转到这里
 *
 */
public class NetFailureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_failure);

        ImageView imageView = (ImageView) findViewById(R.id.net_failed);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager manamger = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = manamger.getActiveNetworkInfo();
                if (info!=null && info.isConnected()){
                    Intent intent = new Intent(NetFailureActivity.this, MainActivity.class);
                    startActivity(intent);
                    NetFailureActivity.this.finish();
                }else{

                    Toast.makeText(NetFailureActivity.this,"请检查网络连接！",Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}
