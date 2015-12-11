package com.l000phone.themovietime.firstpage.search.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.l000phone.themovietime.firstpage.search.adapter.SearchSuggestAdapter;
import com.l000phone.themovietime.firstpage.search.asynctask.SearchSuggestAsyncTask.SearchSuggestCallback;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.search.asynctask.SearchSuggestAsyncTask;
import com.l000phone.themovietime.firstpage.search.searchbean.SearchSuggestBean;
import com.l000phone.themovietime.utils.CitySelect;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchStartFragment extends Fragment implements SearchSuggestCallback{

    private String path = "http://api.m.mtime.cn/Search/SearchSuggestionNew.api";
    private View view;
    private RadioButton first_search_store;
    private ListView first_store_list;
    private String keyword;
    private List<SearchSuggestBean> suggestBeans;
    private SearchSuggestAdapter adapter;

    public SearchStartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.first_search_start, container, false);

        initView();

        Bundle bundle = getArguments();
        keyword = bundle.getString("keyword");

        first_search_store.setText("  去商城搜索"+"("+keyword+")");
        suggestBeans = new ArrayList<>();
        adapter = new SearchSuggestAdapter(getContext(),suggestBeans);
        first_store_list.setAdapter(adapter);

        String params = "locationId="+ CitySelect.getCityId()+"&keyword="+keyword;
        new SearchSuggestAsyncTask(getContext(),this).execute(path, params);

        first_store_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //携带id跳转到电影详情界面
                Intent intent = new Intent();
                intent.putExtra("id",suggestBeans.get(position).getId());
                startActivity(intent);

            }
        });

        return view;
    }

    private void initView() {

        first_search_store = (RadioButton) view.findViewById(R.id.first_search_store);
        first_store_list = (ListView) view.findViewById(R.id.first_store_list);


        //在商城中查看 的点击事件
        first_search_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //携带keyword跳转到商城搜索
                Intent intent = new Intent();


                startActivity(intent);
            }
        });

    }

    //搜索建议回调类
    @Override
    public void getSearchSuggest(List<SearchSuggestBean> list) {

        suggestBeans.clear();
        suggestBeans.addAll(list);
        adapter.notifyDataSetChanged();
    }

}
