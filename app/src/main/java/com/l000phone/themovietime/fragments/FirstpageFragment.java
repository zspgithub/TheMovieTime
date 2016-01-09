package com.l000phone.themovietime.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.l000phone.themovietime.WebViewActivity;
import com.l000phone.themovietime.MainActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.CitySelectActivity;
import com.l000phone.themovietime.firstpage.MyRecyclerView;
import com.l000phone.themovietime.firstpage.adapters.GoodsViewPagerAdapter;
import com.l000phone.themovietime.firstpage.adapters.MoviesHotAdapter;
import com.l000phone.themovietime.firstpage.adapters.RecyclerAdapter;
import com.l000phone.themovietime.firstpage.asynctasks.FirstTopImgAsyncTask;
import com.l000phone.themovietime.firstpage.asynctasks.FirstTopMovieAsyncTask;
import com.l000phone.themovietime.firstpage.asynctasks.FirstTopMovieAsyncTask.FirstTopMovieCallback;
import com.l000phone.themovietime.firstpage.asynctasks.FirstTopMovieAsyncTask.FirstTopCallback;
import com.l000phone.themovietime.firstpage.asynctasks.FirstTopImgAsyncTask.FirstImgCallback;
import com.l000phone.themovietime.firstpage.asynctasks.GoodsAsyncTask;
import com.l000phone.themovietime.firstpage.asynctasks.GoodsAsyncTask.GoodsSubCallback;
import com.l000phone.themovietime.firstpage.asynctasks.GoodsAsyncTask.GoodsGotoPageCallback;
import com.l000phone.themovietime.firstpage.asynctasks.GoodsPagerAsyncTask;
import com.l000phone.themovietime.firstpage.asynctasks.GoodsPagerAsyncTask.GoodsPagerCallback;
import com.l000phone.themovietime.firstpage.asynctasks.MoviesGoodAsyncTask;
import com.l000phone.themovietime.firstpage.asynctasks.MoviesHotAsyncTask.MoviesHotCallback;
import com.l000phone.themovietime.firstpage.asynctasks.MoviesHotAsyncTask;
import com.l000phone.themovietime.firstpage.asynctasks.MoviesGoodAsyncTask.MoviesGoodCallback;
import com.l000phone.themovietime.firstpage.bean.FirstMovieBean;
import com.l000phone.themovietime.firstpage.bean.FirstTopBean;
import com.l000phone.themovietime.firstpage.bean.GoodsGotoPageBean;
import com.l000phone.themovietime.firstpage.bean.GoodsPagerBean;
import com.l000phone.themovietime.firstpage.bean.GoodsSubBean;
import com.l000phone.themovietime.firstpage.bean.MoviesGoodBean;
import com.l000phone.themovietime.firstpage.bean.MoviesGoodDetialBean;
import com.l000phone.themovietime.firstpage.bean.MoviesHotBean;
import com.l000phone.themovietime.firstpage.search.SearchMovieActivity;
import com.l000phone.themovietime.utils.CitySelect;
import com.l000phone.themovietime.utils.FragmentChangeHelper;
import com.l000phone.themovietime.utils.ImageUtils;
import com.l000phone.themovietime.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstpageFragment extends Fragment implements View.OnClickListener, FirstTopMovieCallback, FirstTopCallback, FirstImgCallback, GoodsSubCallback, GoodsGotoPageCallback, GoodsPagerCallback, MoviesHotCallback, MoviesGoodCallback {

    private List<Bitmap> bitmaps = new ArrayList<>();
    //public static final String PATH_TOP = "http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";
    public String PATH_TOP = CitySelect.getUrl();
    public static final String PATH_GOODS = "http://api.m.mtime.cn/PageSubArea/GetFirstPageAdvAndNews.api";
    private String goodsParams = "subSecondParam=17304115#5#1&subFistParam=17728960#5#0&subFifthParam=18159583#16#0&subThirdParam=17304116#5#0";
    public static final String TAG = "FirstpageFragment";
    private List<FirstMovieBean> list = new ArrayList<>();
    private RecyclerAdapter movieAdapter;
    private int index = 0;//给集合设置加载的图片的下标
    private MainActivity mainActivity;
    private ImageUtils imageUtils;
    private List<GoodsGotoPageBean> goodsGotoPageBeans;
    private List<ImageView> goodsPagerViews;//商品定时滑动广告条视图集合
    private GoodsViewPagerAdapter goodsPagerAdapter;//商品定时滑动广告条适配器
    private List<MoviesHotBean> moviesHotBeans;//今日热点集合
    private MoviesHotAdapter moviesHotAdapter;//今日热点适配器

    //声明控件
    private View view;
    private MyRecyclerView mRecyclerView;
    private TextView first_bar_city;
    private ImageView first_bar_img;
    private ImageView first_bar_zoomin;
    private ScrollView first_scrollview;
    private RelativeLayout first_container_one;
    private TextView first_top_movie_titleCn;
    private TextView first_top_movie_ratingFinal;
    private Button first_top_movie_btn;
    private TextView first_top_movie_commonSpecial;
    private RelativeLayout first_movie_hot;
    private TextView first_movie_totalHotMovie;
    private RelativeLayout first_movie_comming;
    private TextView first_movie_totalComingMovie;
    private RelativeLayout first_movie_camera;
    private TextView first_movie_totalCinemaCount;
    private ImageView first_goods_sub1;
    private ImageView first_goods_sub2;
    private ImageView first_goods_sub3;
    private ImageView first_goods_sub4;
    private ViewPager first_viewPager;
    private LinearLayout first_pager_container;
    private ListView first_listView;
    private RelativeLayout first_movie_more;
    private RelativeLayout first_movie_good;
    private RelativeLayout first_movie_good_btm;
    private ImageView first_movie_good_topCover_btm;
    private TextView first_movie_good_title_btm;
    private TextView first_movie_good_desc_btm;
    private ImageView first_movie_good_image_btm;
    private TextView first_movie_good_titleEn_btm;
    private TextView first_movie_good_titleCn;

    //ViewPager定时滑动
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        int index = 0;

        @Override
        public void run() {
            first_viewPager.setCurrentItem(index++);
            handler.postDelayed(runnable, 3000);
            if (index == goodsPagerViews.size()) {
                index = 0;
            }
        }
    };
    private TextView first_movie_news;
    private TextView first_movie_future;
    private TextView first_movie_score;
    private TextView first_movie_evaluate;

    public FirstpageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_firstpage, container, false);

        imageUtils = new ImageUtils();
        mainActivity = (MainActivity) getActivity();
        //初始化控件
        initView();

        //最上边可滑动图片
        new FirstTopMovieAsyncTask(this, this, getContext()).execute(PATH_TOP);
        //商品
        new GoodsAsyncTask(getContext(), this, this).execute(PATH_GOODS, goodsParams);
        //定时滑动广告条
        new GoodsPagerAsyncTask(getContext(), this).execute(PATH_GOODS, goodsParams);
        //今日热点
        new MoviesHotAsyncTask(getContext(), this).execute(PATH_GOODS, goodsParams);
        //每日佳片
        new MoviesGoodAsyncTask(getContext(), this).execute(PATH_GOODS, goodsParams);
        return view;
    }

    //初始化控件
    private void initView() {

        //Bar控件
        first_bar_city = (TextView) view.findViewById(R.id.first_bar_city);
        first_bar_city.setText(CitySelect.getCityName());
        first_bar_city.setOnClickListener(this);
        first_bar_img = (ImageView) view.findViewById(R.id.first_bar_img);
        first_bar_zoomin = (ImageView) view.findViewById(R.id.first_bar_zoomin);
        first_bar_zoomin.setOnClickListener(this);

        //Top可滑动图片
        first_scrollview = (ScrollView) view.findViewById(R.id.first_scrollview);
        first_container_one = (RelativeLayout) view.findViewById(R.id.first_container_one);
        first_top_movie_titleCn = (TextView) view.findViewById(R.id.first_top_movie_titleCn);
        first_top_movie_ratingFinal = (TextView) view.findViewById(R.id.first_top_movie_ratingFinal);
        first_top_movie_btn = (Button) view.findViewById(R.id.first_top_movie_btn);
        first_top_movie_commonSpecial = (TextView) view.findViewById(R.id.first_top_movie_commonSpecial);

        //三个跳转layout
        first_movie_hot = (RelativeLayout) view.findViewById(R.id.first_movie_hot);
        first_movie_totalHotMovie = (TextView) view.findViewById(R.id.first_movie_totalHotMovie);
        first_movie_comming = (RelativeLayout) view.findViewById(R.id.first_movie_comming);
        first_movie_totalComingMovie = (TextView) view.findViewById(R.id.first_movie_totalComingMovie);
        first_movie_camera = (RelativeLayout) view.findViewById(R.id.first_movie_camera);
        first_movie_totalCinemaCount = (TextView) view.findViewById(R.id.first_movie_totalCinemaCount);
        first_movie_hot.setOnClickListener(this);
        first_movie_comming.setOnClickListener(this);
        first_movie_camera.setOnClickListener(this);

        //商品
        first_goods_sub1 = (ImageView) view.findViewById(R.id.first_goods_sub1);
        first_goods_sub2 = (ImageView) view.findViewById(R.id.first_goods_sub2);
        first_goods_sub3 = (ImageView) view.findViewById(R.id.first_goods_sub3);
        first_goods_sub4 = (ImageView) view.findViewById(R.id.first_goods_sub4);

        first_goods_sub1.setOnClickListener(this);
        first_goods_sub2.setOnClickListener(this);
        first_goods_sub3.setOnClickListener(this);
        first_goods_sub4.setOnClickListener(this);

        //定时滑动的广告条
        first_viewPager = (ViewPager) view.findViewById(R.id.first_viewPager);
        goodsPagerViews = new ArrayList<>();
        goodsPagerAdapter = new GoodsViewPagerAdapter(goodsPagerViews);
        first_pager_container = (LinearLayout) view.findViewById(R.id.first_Pager_container);

        //今日热点
        first_movie_more = (RelativeLayout) view.findViewById(R.id.first_movie_more);
        first_movie_more.setOnClickListener(this);
        first_listView = (ListView) view.findViewById(R.id.first_listView);
        moviesHotBeans = new ArrayList<>();
        moviesHotAdapter = new MoviesHotAdapter(getContext(), moviesHotBeans);
        first_listView.setAdapter(moviesHotAdapter);
        //点击ListView的item，跳转到电影详情Activity
        first_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(getActivity(),电影详情activity);
                //startActivity(intent);
            }
        });


        //每日佳片前的四个TextView跳转
        first_movie_news = (TextView) view.findViewById(R.id.first_movie_news);
        first_movie_future = (TextView) view.findViewById(R.id.first_movie_future);
        first_movie_score = (TextView) view.findViewById(R.id.first_movie_score);
        first_movie_evaluate = (TextView) view.findViewById(R.id.first_movie_evaluate);
        first_movie_news.setOnClickListener(this);
        first_movie_future.setOnClickListener(this);
        first_movie_score.setOnClickListener(this);
        first_movie_evaluate.setOnClickListener(this);

        //每日佳片
        first_movie_good = (RelativeLayout) view.findViewById(R.id.first_movie_good);
        first_movie_good.setOnClickListener(this);
        first_movie_good_btm = (RelativeLayout) view.findViewById(R.id.first_movie_good_btm);
        first_movie_good_btm.setOnClickListener(this);
        first_movie_good_topCover_btm = (ImageView) view.findViewById(R.id.first_movie_good_topCover_btm);
        first_movie_good_title_btm = (TextView) view.findViewById(R.id.first_movie_good_title_btm);
        first_movie_good_desc_btm = (TextView) view.findViewById(R.id.first_movie_good_desc_btm);
        first_movie_good_image_btm = (ImageView) view.findViewById(R.id.first_movie_good_image_btm);
        first_movie_good_titleEn_btm = (TextView) view.findViewById(R.id.first_movie_good_titleEn_btm);
        first_movie_good_titleCn = (TextView) view.findViewById(R.id.first_movie_good_titleCn);

    }

    //初始化RecyclerView
    public void initRecyclerView() {

        mRecyclerView = (MyRecyclerView) view.findViewById(R.id.first_recyclerview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        movieAdapter = new RecyclerAdapter(getActivity(), bitmaps);
        mRecyclerView.setAdapter(movieAdapter);

        LogUtil.log(TAG, "----mRecyclerView.setAdapter(movieAdapter)");

        mRecyclerView.setItemScrollCallback(new MyRecyclerView.ItemScrollCallback() {
            @Override
            public void onChange(View view, int postion) {
                int currentPostion = postion;
                if (list != null) {
                    FirstMovieBean firstMovieBean = list.get(currentPostion + 1);
                    first_top_movie_titleCn.setText(firstMovieBean.getTitleCn());
                    first_top_movie_ratingFinal.setText(firstMovieBean.getRatingFinal());
                    first_top_movie_commonSpecial.setText(firstMovieBean.getCommonSpecial());

                }
            }
        });

        movieAdapter.setItemClickCallback(new RecyclerAdapter.ItemClickCallback() {
            @Override
            public void onChange(View view, int position) {

                //携带id跳转到电影详情界面,购票的二级界面
                String id = list.get(position).getMovieId();
                Intent intent = new Intent();

            }
        });
    }

    //movies回调方法
    @Override
    public void getFirstTopMovie(List<FirstMovieBean> list) {

        this.list = list;
        for (int i = 0; i < list.size(); i++) {

            new FirstTopImgAsyncTask(getContext(), this).execute(list.get(i).getImg());
//            imageUtils.loadImg(list.get(i).getImg(),bitmaps,i);
//            movieAdapter.notifyDataSetChanged();
//            LogUtil.log("first",i+"下载图片下标");
        }

        initRecyclerView();

    }

    //上边可滑动图片回调方法
    @Override
    public void getImg(Bitmap bitmap) {

        bitmaps.add(index, bitmap);
        movieAdapter.notifyDataSetChanged();
        index++;

    }

    //Top类回调
    @Override
    public void getFirstTop(FirstTopBean firstTopBean) {

        first_movie_totalHotMovie.setText(firstTopBean.getTotalHotMovie());
        first_movie_totalComingMovie.setText(firstTopBean.getTotalComingMovie());
        first_movie_totalCinemaCount.setText(firstTopBean.getTotalCinemaCount());

    }

    //各个按钮的点击事件
    @Override
    public void onClick(View v) {
        //购票界面
        FragmentChangeHelper payticketHelper;
        Bundle payticketBundle;

        //商品详情
        Intent goodIntent = new Intent(getActivity(), WebViewActivity.class);

        //发现界面
        FragmentChangeHelper discoverHelper;
        Bundle discoverBundle;

        switch (v.getId()) {

            //bar的城市选择,跳转到城市列表界面
            case R.id.first_bar_city:
                Intent intent = new Intent(getActivity(), CitySelectActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;

            //bar的搜索图片
            case R.id.first_bar_zoomin:
                Intent intent1 = new Intent(getActivity(), SearchMovieActivity.class);
                startActivity(intent1);
                break;

            //购票界面的正在热映：
            case R.id.first_movie_hot:
                payticketBundle = new Bundle();
                payticketBundle.putInt("index", 0);
                payticketHelper = new FragmentChangeHelper();
                payticketHelper.setTargetFragment(new PayticketFragment());
                payticketHelper.setIsClearBackStack(true);
                payticketHelper.setBundle(payticketBundle);
                mainActivity.changeFragment(payticketHelper);
                break;
            //购票界面的即将上映：
            case R.id.first_movie_comming:
                payticketBundle = new Bundle();
                payticketBundle.putInt("index", 1);
                payticketHelper = new FragmentChangeHelper();
                payticketHelper.setTargetFragment(new PayticketFragment());
                payticketHelper.setIsClearBackStack(true);
                payticketHelper.setBundle(payticketBundle);
                mainActivity.changeFragment(payticketHelper);
                break;
            //购票界面的找影院：
            case R.id.first_movie_camera:
                payticketBundle = new Bundle();
                payticketBundle.putInt("index", 2);
                payticketHelper = new FragmentChangeHelper();
                payticketHelper.setTargetFragment(new PayticketFragment());
                payticketHelper.setIsClearBackStack(true);
                payticketHelper.setBundle(payticketBundle);
                mainActivity.changeFragment(payticketHelper);
                break;

            //商品1
            case R.id.first_goods_sub1:
                if (goodsGotoPageBeans == null) {
                    return;
                }
                goodIntent.putExtra("url", goodsGotoPageBeans.get(0).getUrl());
                startActivity(goodIntent);
                break;
            //商品2
            case R.id.first_goods_sub2:
                if (goodsGotoPageBeans == null) {
                    return;
                }
                goodIntent.putExtra("url", goodsGotoPageBeans.get(1).getUrl());
                startActivity(goodIntent);
                break;
            //商品3
            case R.id.first_goods_sub3:
                if (goodsGotoPageBeans == null) {
                    return;
                }
                goodIntent.putExtra("url", goodsGotoPageBeans.get(2).getUrl());
                startActivity(goodIntent);
                break;
            //商品4
            case R.id.first_goods_sub4:
                if (goodsGotoPageBeans == null) {
                    return;
                }
                goodIntent.putExtra("url", goodsGotoPageBeans.get(3).getUrl());
                startActivity(goodIntent);
                break;

            //今日热点的更多 点击事件
            case R.id.first_movie_more:
                FragmentChangeHelper moreMovieHelper = new FragmentChangeHelper();
                moreMovieHelper.setTargetFragment(new DiscoverFragment());
                moreMovieHelper.setIsClearBackStack(true);
                mainActivity.changeFragment(moreMovieHelper);
                break;

            //每日佳片上面的四个TextView点击事件
            case R.id.first_movie_news:
                discoverBundle = new Bundle();
                discoverBundle.putInt("index", 0);
                discoverHelper = new FragmentChangeHelper();
                discoverHelper.setTargetFragment(new DiscoverFragment());
                discoverHelper.setIsClearBackStack(true);
                discoverHelper.setBundle(discoverBundle);
                mainActivity.changeFragment(discoverHelper);
                break;
            case R.id.first_movie_future:
                discoverBundle = new Bundle();
                discoverBundle.putInt("index", 1);
                discoverHelper = new FragmentChangeHelper();
                discoverHelper.setTargetFragment(new DiscoverFragment());
                discoverHelper.setIsClearBackStack(true);
                discoverHelper.setBundle(discoverBundle);
                mainActivity.changeFragment(discoverHelper);
                break;
            case R.id.first_movie_score:
                discoverBundle = new Bundle();
                discoverBundle.putInt("index", 2);
                discoverHelper = new FragmentChangeHelper();
                discoverHelper.setTargetFragment(new DiscoverFragment());
                discoverHelper.setIsClearBackStack(true);
                discoverHelper.setBundle(discoverBundle);
                mainActivity.changeFragment(discoverHelper);
                break;
            case R.id.first_movie_evaluate:
                discoverBundle = new Bundle();
                discoverBundle.putInt("index", 3);
                discoverHelper = new FragmentChangeHelper();
                discoverHelper.setTargetFragment(new DiscoverFragment());
                discoverHelper.setIsClearBackStack(true);
                discoverHelper.setBundle(discoverBundle);
                mainActivity.changeFragment(discoverHelper);
                break;

            //每日佳片
            case R.id.first_movie_good:
                //跳转到发现的排行榜
                discoverBundle = new Bundle();
                discoverBundle.putInt("index", 2);
                discoverHelper = new FragmentChangeHelper();
                discoverHelper.setTargetFragment(new DiscoverFragment());
                discoverHelper.setIsClearBackStack(true);
                discoverHelper.setBundle(discoverBundle);
                mainActivity.changeFragment(discoverHelper);
                break;
            case R.id.first_movie_good_btm:
                //跳转到详情界面

                break;
        }
    }

    //商品详情回调类，进入webView
    @Override
    public void getGoodsGotoPage(List<GoodsGotoPageBean> list) {
        goodsGotoPageBeans = list;
    }

    //四个商品块的回调类
    @Override
    public void getGoodsSub(List<GoodsSubBean> list) {
        Log.d("zsp","-------------"+list);
        try{
        imageUtils.loadImage(list.get(0).getImage(), first_goods_sub1);
        imageUtils.loadImage(list.get(1).getImage(), first_goods_sub2);
        imageUtils.loadImage(list.get(2).getImage(), first_goods_sub3);
        imageUtils.loadImage(list.get(3).getImage(), first_goods_sub4);}catch (Exception e){
            e.printStackTrace();
        }

    }

    //中间定时滑动广告条的回调类
    @Override
    public void getGoodsPager(List<GoodsPagerBean> list) {

        LogUtil.log("first", "定时滑动开始下载图片");
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        for (int i = 0; i < list.size(); i++) {

            LogUtil.log("first", "定时滑动图片地址—" + list.get(i).getImg());
            ImageView imageView = null;
            imageView = new ImageView(getActivity());
            imageView.setLayoutParams(params);
            imageUtils.loadImage(list.get(i).getImg(), imageView);
            goodsPagerViews.add(imageView);
            goodsPagerAdapter.notifyDataSetChanged();
            ImageView pointImg = new ImageView(getActivity());
            pointImg.setBackgroundResource(R.mipmap.point);
            pointImg.setPadding(5, 5, 0, 0);
            first_pager_container.addView(pointImg);

        }

        handler.post(runnable);//让viewPager定时滑动

        //设置适配器
        first_viewPager.setAdapter(goodsPagerAdapter);
        LogUtil.log("first", "定时滑动集合长度：" + goodsPagerViews.size());
        //给ViewPager设置监听
        first_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (positionOffset > 0.5){
//                    if (position == goodsPagerViews.size()){
//                        first_viewPager.setCurrentItem(0);
//                    }else{
//                        first_viewPager.setCurrentItem(position+1);
//                    }
//                }else if (position < -0.5){
//                    if (position == goodsPagerViews.size()){
//                        first_viewPager.setCurrentItem(position-1);
//                    }else{
//                        first_viewPager.setCurrentItem(goodsPagerViews.size());
//                    }
//                }
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < first_pager_container.getChildCount(); i++) {
                    first_pager_container.getChildAt(i).setBackgroundResource(R.mipmap.point);
                }
                first_pager_container.getChildAt(position).setBackgroundResource(R.mipmap.point_f);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //下边今日热点回调类
    @Override
    public void getMoviesHot(List<MoviesHotBean> list) {

        moviesHotBeans.clear();
        moviesHotBeans.addAll(list);
        moviesHotAdapter.notifyDataSetChanged();
    }

    //最下边每日佳片回调
    @Override
    public void getMoviesGood(MoviesGoodBean bean) {

        MoviesGoodDetialBean detialBean = bean.getMovie();
        first_movie_good_title_btm.setText(bean.getTitle());
        first_movie_good_desc_btm.setText(detialBean.getDesc());
        first_movie_good_titleEn_btm.setText(detialBean.getTitleEn());
        first_movie_good_titleCn.setText(detialBean.getTitleCn());

        imageUtils.loadImage(bean.getTopCover(), first_movie_good_topCover_btm);
        imageUtils.loadImage(detialBean.getImage(), first_movie_good_image_btm);

    }
}
