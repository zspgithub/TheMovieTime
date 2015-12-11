package com.l000phone.themovietime.firstpage.search.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.search.adapter.SearchResultAdapter;
import com.l000phone.themovietime.firstpage.search.asynctask.SearchResultAsyncTask;
import com.l000phone.themovietime.firstpage.search.searchbean.SearchResultBean;
import com.l000phone.themovietime.utils.CitySelect;
import com.l000phone.themovietime.firstpage.search.asynctask.SearchResultAsyncTask.SearchResultCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果Fragment
 *
 * A simple {@link Fragment} subclass.
 */
public class SearchResultListFragment extends Fragment implements SearchResultCallback{

    private String path = "http://api.m.mtime.cn/Showtime/SearchVoice.api";
    private int pageIndex = 1;//返回结果的页码下标
    private int searchType;//搜索类型
    private String locationId = CitySelect.getCityId();
    private String keyword;
    private  String params;
    private View view;
    private PullToRefreshListView first_search_result_list;
    private List<SearchResultBean> beans;
    private SearchResultAdapter adapter;

    public SearchResultListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.first_search_result_list, container, false);

        initView();

        Bundle bundle = getArguments();
        keyword =  bundle.getString("keyword");
        int index = bundle.getInt("index");

        params = "locationId="+locationId+"&searchType=3&pageIndex="+pageIndex+"&keyword="+keyword;

        beans = new ArrayList<>();
        adapter = new SearchResultAdapter(getContext(),beans);
        first_search_result_list.setAdapter(adapter);

        if (index == 0){
            new SearchResultAsyncTask(getContext(),this).execute(path,params);
        }else{
            view = inflater.inflate(R.layout.first_search_result_empty,null);
        }

        return view;
    }

    private void initView() {

        first_search_result_list = (PullToRefreshListView) view.findViewById(R.id.first_search_result_list);

        ILoadingLayout loadingLayout = first_search_result_list.getLoadingLayoutProxy(true,true);
        loadingLayout.setPullLabel("上拉加载更多...");
        loadingLayout.setRefreshingLabel("正在载入...");
        loadingLayout.setReleaseLabel("释放加载...");

        first_search_result_list.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        first_search_result_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            //上拉刷新
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageIndex++;
                new SearchResultAsyncTask(getContext(), SearchResultListFragment.this).execute(path, params);
            }
        });

        first_search_result_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           //携带id跳转到电影详情界面
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(),);
//                intent.putExtra("id",beans.get(position).getId());
//
//                startActivity(intent);
            }
        });


    }

    //搜索结果回调类
    @Override
    public void getSearchResult(List<SearchResultBean> list) {
        beans.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
