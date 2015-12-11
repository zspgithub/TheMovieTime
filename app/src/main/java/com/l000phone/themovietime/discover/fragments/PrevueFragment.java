package com.l000phone.themovietime.discover.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.Activitys.PrevueVideoActivity;
import com.l000phone.themovietime.discover.adapters.PrevueListViewAdapter;
import com.l000phone.themovietime.discover.bean.PrevueListView;
import com.l000phone.themovietime.discover.bean.PrevueSubArea;
import com.l000phone.themovietime.discover.json.PrevueListViewJson;
import com.l000phone.themovietime.discover.json.PrevueSubAreaJson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class PrevueFragment extends Fragment {


    private View view;
    private ImageView prevue_pageSubArea;
    private TextView prevue_pageSubArea_title;
    private PullToRefreshListView prevue_pullToRefresh;
    private static final String SubAreaUrl="http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";
    private static final String PrevueListUrl="http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    private String mp4Url;
    private String hightUrl;
    private List<PrevueListView>listViews= new ArrayList<PrevueListView>();
    private PrevueListViewAdapter adapter;


    public PrevueFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.discover_fragment_prevue, container, false);
        initView();
        initPrevueSubArea();
        loadingData();
        adapter = new PrevueListViewAdapter(getContext(),listViews);
        prevue_pullToRefresh.setAdapter(adapter);
        //进入界面自动刷新
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                prevue_pullToRefresh.setRefreshing(true);

            }
        }, 500); //延迟500ms执行

        return view;
    }


    private void loadingData() {
        HttpUtils httpUtils=new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, PrevueListUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                List<PrevueListView> listView= PrevueListViewJson.getJson(responseInfo.result);
                listViews.addAll(listView);
                adapter.notifyDataSetChanged();
                prevue_pullToRefresh.onRefreshComplete();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });

    }

    private void initPrevueSubArea() {
        HttpUtils httpUtils=new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, SubAreaUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                PrevueSubArea subArea= PrevueSubAreaJson.getJson(responseInfo.result);
                prevue_pageSubArea_title.setText(subArea.getTitle());
                BitmapUtils bitmapUtils=new BitmapUtils(getContext());
                bitmapUtils.display(prevue_pageSubArea,subArea.getImageUrl());
                mp4Url = subArea.getMp4Url();
                hightUrl = subArea.getHightUrl();
            }
            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });


        }
    private void initView() {
        prevue_pageSubArea = (ImageView) view.findViewById(R.id.prevue_pageSubArea);
        prevue_pageSubArea_title = (TextView) view.findViewById(R.id.prevue_pageSubArea_title);
        prevue_pullToRefresh = (PullToRefreshListView) view.findViewById(R.id.prevue_pullToRefresh);

        prevue_pullToRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        prevue_pullToRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                listViews.clear();
                loadingData();
            }
        });


        prevue_pageSubArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrevueVideoActivity.class);
                intent.putExtra("ma4Url", mp4Url);
                startActivity(intent);
            }
        });

        prevue_pullToRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url=listViews.get(position).getUrl();
                Intent intent=new Intent(getActivity(),PrevueVideoActivity.class);
                intent.putExtra("mp4Url",url);
                startActivity(intent);

            }
        });

    }



}
