package com.l000phone.themovietime.shop.shopActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.network.ShopfirstBitmapNetwork;
import com.l000phone.themovietime.shop.shopAdapter.ShopBuyBottomGridviewAdapter;
import com.l000phone.themovietime.shop.shopAdapter.ShopBuyListviewAdapter;
import com.l000phone.themovietime.shop.shopAdapter.ShopBuyViewPagerAdapter;
import com.l000phone.themovietime.shop.shopDataParse.ShopBuyDataParse;
import com.l000phone.themovietime.shop.shopbuybean.ContentList;
import com.l000phone.themovietime.shop.shopbuybean.Goods;
import com.l000phone.themovietime.shop.shopbuybean.Goodssss;
import com.l000phone.themovietime.shop.utils.MyGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopBuyActivity extends BaseActivity implements View.OnClickListener{

    private ListView listView;
    private View view;
    private String id;
    private ShopBuyListviewAdapter adapter;
    private List<ContentList> contentLists = new ArrayList<>();
    private List<Map<String,Object>> listmap = new ArrayList<>();
    private Fragment fragment;
    private Bundle bundle;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;

            switch (what){

                //返回1是contentList
                case 1:

                    List<ContentList> cont01 = (List<ContentList>) msg.obj;

                    contentLists.addAll(cont01);

                    Map<String,Object> map = new HashMap<>();

                    map.put("contentlist", contentLists);

                    listmap.add(map);



                    break;

                //返回2是listview 的接下来数据

                case 2:
                    List<ContentList> con02 = (List<ContentList>) msg.obj;

                    contentLists.addAll(con02);

                    adapter.notifyDataSetChanged();
                    break;

                //返回3是主界面的各种数据
                case 3:
                    Map<String,Object> maps = (Map<String, Object>) msg.obj;

                   Goods goodss = (Goods) maps.get("goods");

                    //得到viewpager的图片资源
                    List<String> stringList = (List<String>) maps.get("listString");

                        int size = stringList.size();
                        viewpagerList = new ArrayList<>();
                        for (int i = 0; i <size; i++) {
                            ImageView imageView = new ImageView(ShopBuyActivity.this);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            ShopfirstBitmapNetwork.CellABitMapUtils(ShopBuyActivity.this, imageView, stringList.get(i));
                            viewpagerList.add(imageView);
                        }

                    ShopBuyViewPagerAdapter viewpageradapter = new ShopBuyViewPagerAdapter(viewpagerList);

                    viewPager.setAdapter(viewpageradapter);
                    if (viewpagerList.size()!=0){
                    initDot(view);
                    }
                    String add = " "+goodss.getNote().toString();

                    List<String> listString = new ArrayList<>();

                    listString.add(add);

                    Map<String,Object> map1 = new HashMap<>();

                    map1.put("goods", listString);

                    listmap.add(map1);

                    if(listmap.size() == 2){
                        String second = "貌似没有什么内容。。";
                        List<String> list = new ArrayList<>();
                        list.add(second);
                        Map<String,Object> map2 = new HashMap<>();
                        map2.put("second",list);
                        listmap.add(map2);
                        adapter = new ShopBuyListviewAdapter(listmap,ShopBuyActivity.this);
                        adapter.setIndex(1);

                        listView.setAdapter(adapter);
                        ShopBuyDataParse.parseNext(id, handler);
                    }



                    name.setText(goodss.getName());
                    double parseDouble = Double.parseDouble(goodss.getMinSalePrice());
                    double market = Double.parseDouble(goodss.getMarketPrice());
                    price.setText("¥"+parseDouble/100);
                    oldPrice.setText("市场价   " + "¥" + market / 100);
                    tvnew.setText(goodss.getStyle().getGoodsTag());
                    tvnew.setBackgroundColor(Color.parseColor(goodss.getStyle().getBackground()));

                    int total = Integer.parseInt(goodss.getReviewInfo().getTotalCount());
                    int hao = Integer.parseInt(goodss.getReviewInfo().getGoodCount());
                    if(total != 0){
                        pinglunpeople.setVisibility(View.VISIBLE);
                        hapingBt.setVisibility(View.VISIBLE);
                        haipingPeople.setVisibility(View.VISIBLE);

                        pinglunpeople.setText("(" + total + "人评论)");

                        haipingPeople.setText((hao/total)*100+"%");
                    }
                    break;

                case 55:
                   List<Goodssss> goodssssList = (List<Goodssss>) msg.obj;

                   ShopBuyBottomGridviewAdapter abAdapter = new ShopBuyBottomGridviewAdapter(goodssssList,ShopBuyActivity.this);

                    footerGridview.setAdapter(abAdapter);
                    break;
            }
        }
    };



    private Goods goods;
    private ViewPager viewPager;
    private List<ImageView> viewpagerList;
    private TextView name;
    private TextView price;
    private TextView oldPrice;
    private TextView tvnew;
    private TextView pinglunpeople;
    private TextView hapingBt;
    private TextView haipingPeople;
    private RadioGroup radioGroup;
    private TextView rgtv01;
    private TextView rgtv02;
    private TextView rgtv03;
    private View footer;
    private MyGridView footerGridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_buy);
        initBundle();
        initView();
    }


    private void initBundle() {

        //获得传过来的id
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");

    }

    private void initView() {
        //初始化view
        listView = (ListView) findViewById(R.id.shop_buy_listview);

        //找到加入到listview上部的布局，转化为view
        view = LayoutInflater.from(this).inflate(R.layout.shop_buy_heard_add,null);
        //找到加入到listview下部的布局，转化为view
        footer = LayoutInflater.from(this).inflate(R.layout.shop_buy_addfotter,null);

        listView.addHeaderView(view);
        listView.addFooterView(footer);

        viewPager = (ViewPager) view.findViewById(R.id.shop_buy_viewpager);
        footerGridview = (MyGridView) footer.findViewById(R.id.shop_buy_addfooterGridView);

        name = (TextView) view.findViewById(R.id.shop_buy_name);
        price = (TextView) view.findViewById(R.id.shop_buy_nowprice);
        oldPrice = (TextView) view.findViewById(R.id.shop_buy_oldprice);
        pinglunpeople = (TextView) view.findViewById(R.id.shop_buy_tv_pinglun);
        hapingBt = (TextView) view.findViewById(R.id.shop_buy_tv_haoping);
        haipingPeople = (TextView) view.findViewById(R.id.shop_buy_tv_pinglunPecent);
        radioGroup = (RadioGroup) view.findViewById(R.id.shop_buy_viewpager_dotgroup);
        //联网加载litview里的数据
        ShopBuyDataParse.parse(id, handler);
        //联网加载view上部布局的数据
        ShopBuyDataParse.firstDataParse(id, handler);
        //联网加载GridView里的数据
        ShopBuyDataParse.shopbuygridviewParse(id,handler);
        initClick(view);


    }

    private void initClick(View view) {
        rgtv01 = (TextView) view.findViewById(R.id.shop_buy_tv_tuwenixnagqing);
        rgtv02 = (TextView) view.findViewById(R.id.shop_buy_tv_canshuguige);
        rgtv03 = (TextView) view.findViewById(R.id.shop_buy_tv_goumaixuzhi);
        tvnew = (TextView) view.findViewById(R.id.shop_buy_new);
        rgtv01.setTextColor(Color.parseColor("#0075C4"));

        rgtv01.setOnClickListener(this);
        rgtv02.setOnClickListener(this);
        rgtv03.setOnClickListener(this);
    }

    private void initDot(View view) {
        final List<RadioButton> radioButtonList = new ArrayList<>();
        int size = viewpagerList.size();
            for (int i = 0; i < size; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButtonList.add(radioButton);
                radioGroup.addView(radioButton);
            }
            radioGroup.check(radioButtonList.get(0).getId());


            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                }

                @Override
                public void onPageSelected(int position) {

                    radioGroup.check(radioButtonList.get(position).getId());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_buy_tv_tuwenixnagqing:
                chooseColor();
                rgtv01.setTextColor(Color.parseColor("#0075C4"));
                adapter.setIndex(1);
                adapter.notifyDataSetChanged();
                break;

            case R.id.shop_buy_tv_canshuguige:
                chooseColor();
                rgtv02.setTextColor(Color.parseColor("#0075C4"));
                adapter.setIndex(3);
                adapter.notifyDataSetChanged();
                break;

            case R.id.shop_buy_tv_goumaixuzhi:
                chooseColor();
                rgtv03.setTextColor(Color.parseColor("#0075C4"));
                adapter.setIndex(2);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void chooseColor(){
        rgtv01.setTextColor(Color.parseColor("#333333"));
        rgtv02.setTextColor(Color.parseColor("#333333"));
        rgtv03.setTextColor(Color.parseColor("#333333"));
    }
}
