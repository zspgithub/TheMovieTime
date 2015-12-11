package com.l000phone.themovietime.shop.shopActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;

public class ShopSerchActivity extends BaseActivity {

    private EditText editText;
    private Editable text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_serch);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.shop_search_edsearch);

    }

    public void search(View view){

        switch (view.getId()){
            case R.id.shop_search_imgBack:
                ShopSerchActivity.this.finish();
                break;

            case R.id.shop_search_search:
                String content = editText.getText().toString();

                if(!TextUtils.isEmpty(content)){
                    Intent intent = new Intent(ShopSerchActivity.this,ShopBuyLotsGridActivity.class);

                    Bundle bundle = new Bundle();

                    bundle.putString("name",content);

                    bundle.putString("categoryId1","0");

                    intent.putExtras(bundle);

                    startActivity(intent);

                    ShopSerchActivity.this.finish();
                }
                break;
        }
    }
}
