package com.l000phone.themovietime.shop.shopActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;


import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.shopAdapter.ShopLotsGridviewAdapter;
import com.l000phone.themovietime.shop.shopDataParse.ShopBuyDataParse;
import com.l000phone.themovietime.shop.shopbuybean.LotsgoodsList;
import com.l000phone.themovietime.shop.utils.AutoLoadListener;

import java.util.ArrayList;
import java.util.List;

public class ShopBuyLotsGridActivity extends BaseActivity {

    private GridView gridView;
    private int page = 1;
    private List<LotsgoodsList> lotsgoodsLists = new ArrayList<>();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;

            switch (what){

                case 1:

                    lists = (List<LotsgoodsList>) msg.obj;

                    lotsgoodsLists.addAll(lists);

                    if(page == 1){
                        adapter = new ShopLotsGridviewAdapter(lotsgoodsLists,ShopBuyLotsGridActivity.this);

                        gridView.setAdapter(adapter);
                    }else {
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }



                    break;
            }
        }
    };
    private String name;
    private List<LotsgoodsList> lists;
    private String categoryId1;
    private ShopLotsGridviewAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_buy_lots_grid);
        //初始化传过来的bundle
        initBundle();

        initView();
    }

    private void initBundle() {

        Bundle bundle = getIntent().getExtras();

        name = bundle.getString("name");
        categoryId1 = bundle.getString("categoryId1");
    }

    private void initView() {

        gridView = (GridView) findViewById(R.id.shop_buy_lots_gridview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ShopBuyDataParse.lotsGridShopBuy(name, page + "", categoryId1, handler);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String goodsId = lists.get(position).getGoodsId();
                Log.d("BU", goodsId);
                Intent intent = new Intent(ShopBuyLotsGridActivity.this, ShopBuyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", goodsId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        AutoLoadListener autoLoadListener = new AutoLoadListener(new AutoLoadListener.AutoLoadCallBack() {
            @Override
            public void execute() {
                progressBar.setVisibility(View.VISIBLE);
                page++;
                ShopBuyDataParse.lotsGridShopBuy(name, page+"", categoryId1, handler);
            }
        });

        gridView.setOnScrollListener(autoLoadListener);
    }
}
