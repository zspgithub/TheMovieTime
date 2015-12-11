package com.l000phone.themovietime.discover.fragments;


import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.Activitys.TopListDetailsActivity;
import com.l000phone.themovietime.discover.adapters.TopListListViewAdapter;
import com.l000phone.themovietime.discover.bean.TopListListView;
import com.l000phone.themovietime.discover.bean.TopListSubArea;
import com.l000phone.themovietime.discover.json.TopListListViewJson;
import com.l000phone.themovietime.discover.json.TopListSubAreaJson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class TopListFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageView topListSubArea;
    private TextView topListSubAreaTitle;
    private static final String SubAreaUrl="http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";
    private static final String ListViewUrl="http://api.m.mtime.cn/TopList/TopListOfAll.api?pageIndex=";
    private int pageIndex=1;
    private Button mTimeList;
    private Button chinaList;
    private Button globleList;
    private List<TopListListView>listListViews=new ArrayList<TopListListView>();
    private PullToRefreshListView topListPullToRefresh;
    private TopListListViewAdapter adapter;
    private String id;
    private String title;
    private String type;

    public TopListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.discover_fragment_top_list, container, false);
        initView();
        initSubArea(SubAreaUrl);
        loadListView(ListViewUrl + pageIndex);
        adapter = new TopListListViewAdapter(getContext(),listListViews);
        topListPullToRefresh.setAdapter(adapter);
        //进入界面自动刷新
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                topListPullToRefresh.setRefreshing(true);
            }
        }, 500); //延迟500ms执行

        return view;
    }

    private void loadListView(String url) {
        HttpUtils httpUtils=new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //Log.d("test",responseInfo.result);
                List<TopListListView>list= TopListListViewJson.getJson(responseInfo.result);
                listListViews.addAll(list);
                adapter.notifyDataSetChanged();
                topListPullToRefresh.onRefreshComplete();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });


    }



    private void initSubArea(final String subAreaUrl) {
        HttpUtils httpUtils=new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, subAreaUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //Log.d("test",responseInfo.result);
                TopListSubArea subArea= TopListSubAreaJson.getJson(responseInfo.result);
                //Log.d("test",subArea.getTitle());
                topListSubAreaTitle.setText(subArea.getTitle());
                BitmapUtils bitmapUtils=new BitmapUtils(getContext());
                bitmapUtils.display(topListSubArea,subArea.getImageUrl());
                id = subArea.getId();
                title = subArea.getTitle();
                type = subArea.getType();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });

    }

    private void initView() {
        topListSubArea = (ImageView) view.findViewById(R.id.topList_pageSubArea);
        topListSubAreaTitle = (TextView) view.findViewById(R.id.topList);
        mTimeList = (Button) view.findViewById(R.id.topList_mtime);
        chinaList = (Button) view.findViewById(R.id.topList_china);
        globleList = (Button) view.findViewById(R.id.topList_globle);
        topListPullToRefresh = (PullToRefreshListView) view.findViewById(R.id.topList_pullToRefresh);

//        mTimeList.setOnClickListener(this);
//        chinaList.setOnClickListener(this);
//        globleList.setOnClickListener(this);

        topListPullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        topListPullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex = 1;
                listListViews.clear();
                loadListView(ListViewUrl + pageIndex);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                loadListView(ListViewUrl + pageIndex);

            }
        });

        topListSubArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TopListDetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("type",type);
                intent.putExtra("subTitle",title);
                intent.putExtra("summary","");
                startActivity(intent);
            }
        });

        topListPullToRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),TopListDetailsActivity.class);
                //Log.d("test",position+"++++++++++++++++++++++++++++++++++++");
                intent.putExtra("id",listListViews.get(position-1).getId());
                intent.putExtra("type",listListViews.get(position-1).getType());
                intent.putExtra("subTitle",listListViews.get(position-1).getTopListNameCn());
                intent.putExtra("summary",listListViews.get(position-1).getSummary());
                startActivity(intent);
            }
        });

    }



    @Override
    public void onClick(View v) {

    }
}
