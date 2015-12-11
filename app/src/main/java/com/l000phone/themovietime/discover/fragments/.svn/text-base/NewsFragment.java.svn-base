package com.l000phone.themovietime.discover.fragments;


import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.adapters.NewsListViewAdapter;
import com.l000phone.themovietime.discover.bean.NewsListView;
import com.l000phone.themovietime.discover.bean.NewsSubArea;
import com.l000phone.themovietime.discover.json.NewsListViewJson;
import com.l000phone.themovietime.discover.json.NewsSubAreaJson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {

    private int i=1;
    private View view;
    private PullToRefreshListView pullToRefreshListView;
    private TextView title;
    private ImageView pageSubArea;
    private static final String SubAreaUrl="http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";
    private static final String listViewUrl="http://api.m.mtime.cn/News/NewsList.api?pageIndex=";

    private String newsSubAreaId;
    private List<NewsListView> newsListViews=new ArrayList<NewsListView>();
    private NewsListViewAdapter adapter;
    public NewsFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.discover_fragment_news, container, false);
        //初始化控件
        initView();
        //初始化页面最顶端
        initSubArea(SubAreaUrl);
        //下载listView的第一页数据
        initListView(listViewUrl + i);
        adapter = new NewsListViewAdapter(getContext(),newsListViews);
        pullToRefreshListView.setAdapter(adapter);
        //设置刷新
        initPullToRefresh();

        //进入界面自动刷新
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshListView.setRefreshing(true);
            }
        }, 500); //延迟500ms执行

        return view;
    }


    private void initView() {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.news_pullToRefresh);
        pageSubArea = (ImageView) view.findViewById(R.id.news_pageSubArea);
        title = (TextView) view.findViewById(R.id.news_pageSubArea_title);

    }

    //初始化页面最顶端
    private void initSubArea(String subAreaUrl){
        HttpUtils httpUtils=new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, SubAreaUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                NewsSubArea subArea = NewsSubAreaJson.getJson(responseInfo.result);
                newsSubAreaId = subArea.getNewsID();
                title.setText(subArea.getTitle());
                BitmapUtils bitmapUtils = new BitmapUtils(getContext());
                bitmapUtils.display(pageSubArea, subArea.getImageUrl());
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    //下载listView的数据并进行初始化
    private void initListView(String listViewUrl){
        HttpUtils listHttp=new HttpUtils(5000);
        listHttp.send(HttpRequest.HttpMethod.GET, listViewUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                List<NewsListView>news = NewsListViewJson.getJson(responseInfo.result);
                newsListViews.addAll(news);

                adapter.notifyDataSetChanged();
                pullToRefreshListView.onRefreshComplete();
            }
            @Override
            public void onFailure(HttpException error, String msg) {
            }
        });

    }

    //设置上拉和下拉模式
    public void initPullToRefresh(){
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                i=1;
                newsListViews.clear();
                initListView(listViewUrl + i);
                pullToRefreshListView.setAdapter(adapter);
               // pullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                i++;
                initListView(listViewUrl + i);
               // pullToRefreshListView.onRefreshComplete();
            }
        });

    }


}

















