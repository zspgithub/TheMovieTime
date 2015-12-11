package com.l000phone.themovietime.discover.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.adapters.TopListDetailsAdapter1;
import com.l000phone.themovietime.discover.adapters.TopListDetailsAdapter2;
import com.l000phone.themovietime.discover.bean.TopListDetailsBean1;
import com.l000phone.themovietime.discover.bean.TopListDetailsBean2;
import com.l000phone.themovietime.discover.json.TopListDetailsJson1;
import com.l000phone.themovietime.discover.json.TopListDetailsJson2;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class TopListDetailsActivity extends BaseActivity {
    private int pageIndex=1;
    private String id;
    private String type;
    private String TopListDetailsUrl="http://api.m.mtime.cn/TopList/TopListDetails.api?pageIndex=";
    private String UrlId="&topListId=";
    private String subTitle;
    private String summary;

    private List<TopListDetailsBean1>list1=new ArrayList<TopListDetailsBean1>();
    private List<TopListDetailsBean2>list2=new ArrayList<TopListDetailsBean2>();
    private PullToRefreshListView pullToRefresh;
    private TopListDetailsAdapter1 adapter1;
    private TopListDetailsAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_list_details);
        Intent intent=getIntent();
        type=intent.getStringExtra("type");
        id=intent.getStringExtra("id");
        subTitle = intent.getStringExtra("subTitle");
        summary = intent.getStringExtra("summary");
        initView();

        initAdapter();
       // 进入界面自动刷新
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefresh.setRefreshing(true);
            }
        }, 500); //延迟500ms执行


    }

    private void loadData1(String url) {
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                List<TopListDetailsBean1> beans1 = TopListDetailsJson1.getJson(responseInfo.result);
                list1.addAll(beans1);
                adapter1.notifyDataSetChanged();
                pullToRefresh.onRefreshComplete();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }

    private void loadData2(String url){
        HttpUtils httpUtils=new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                List<TopListDetailsBean2>beans2= TopListDetailsJson2.getJson(responseInfo.result);
                //Log.d("test",responseInfo.result);
                list2.addAll(beans2);
                adapter2.notifyDataSetChanged();
                pullToRefresh.onRefreshComplete();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }



    private void initAdapter() {

        if (type.equals("2")){
            //loadData2(TopListDetailsUrl+pageIndex+UrlId+id);
            adapter2 = new TopListDetailsAdapter2(this,list2);
            pullToRefresh.setAdapter(adapter2);

        }else if (type.equals("1")||type.equals("0")){
            //loadData1(TopListDetailsUrl+pageIndex+UrlId+id);
            adapter1 = new TopListDetailsAdapter1(this,list1);
            pullToRefresh.setAdapter(adapter1);
        }


    }

    private void initView() {
        TextView tvSubTitle= (TextView) findViewById(R.id.topListDetails_SubTitle);
        TextView tvSummary= (TextView) findViewById(R.id.topListDetails_summary);
        pullToRefresh = (PullToRefreshListView) findViewById(R.id.topListDetails_pullToRefresh);

        tvSubTitle.setText(subTitle);
        tvSummary.setText(summary);

        pullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex=1;
                if (type.equals("2")){
                    list2.clear();
                    loadData2(TopListDetailsUrl+pageIndex+UrlId+id);
                }else if (type.equals("1")||type.equals("0")){
                    list1.clear();
                    loadData1(TopListDetailsUrl+pageIndex+UrlId+id);
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                if (type.equals("2")){
                    loadData2(TopListDetailsUrl+pageIndex+UrlId+id);
                }else if (type.equals("1")||type.equals("0")){
                    loadData1(TopListDetailsUrl+pageIndex+UrlId+id);
                }

            }
        });

    }




}
