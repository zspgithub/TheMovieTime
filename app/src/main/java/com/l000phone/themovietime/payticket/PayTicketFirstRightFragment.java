package com.l000phone.themovietime.payticket;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.adapters.PayTicketShangyingListAdapter;
import com.l000phone.themovietime.payticket.adapters.PayticketShangyingRecycleAdapter;
import com.l000phone.themovietime.payticket.bean.PayticketShangyingBean;
import com.l000phone.themovietime.payticket.bean.PayticketShangyingListNools;
import com.l000phone.themovietime.payticket.bean.PayticketShangyingTopNools;
import com.l000phone.themovietime.utils.ImageUtils;
import com.l000phone.themovietime.utils.LogUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayTicketFirstRightFragment extends Fragment {
    private String url = "http://api.m.mtime.cn/Movie/MovieComingNew.api?locationId=290";
    private String imgUrl = "http://static1.mtime.cn/feature/mobile/item/2015/banner/1113/750210.html";
    private String TAG = "ssssss";
    private List<PayticketShangyingBean> moviecomings = new ArrayList<>();
    private List<PayticketShangyingBean> attention = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListView listView;
    private ImageUtils imageUtils = new ImageUtils();
    private PayTicketShangyingListAdapter listAdapter;
    private PayticketShangyingRecycleAdapter recyadcleApter;

    public PayTicketFirstRightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_ticket_first_right, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.payticket_shangying_recycleView_listView);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.payticket_shangying_top_recycleview,null,false);
        listView.addHeaderView(v);
        listAdapter = new PayTicketShangyingListAdapter(moviecomings, getContext());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ReyingInfoActivity.class);
                intent.putExtra("id", moviecomings.get(position - 1).getId());
                intent.putExtra("img", moviecomings.get(position - 1).getImage());
                intent.putExtra("type", moviecomings.get(position - 1).getType());
                intent.putExtra("name", moviecomings.get(position - 1).getTitle());
                intent.putExtra("year", moviecomings.get(position - 1).getrYear());
                intent.putExtra("month", moviecomings.get(position - 1).getrMonth());
                intent.putExtra("day", moviecomings.get(position - 1).getrDay());
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) v.findViewById(R.id.payticket_shangying_top_recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyadcleApter = new PayticketShangyingRecycleAdapter(attention, getContext());
        recyclerView.setAdapter(recyadcleApter);

        WebView webView  = (WebView) v.findViewById(R.id.payticket_shangying_top_recycleView_webview);
        webView.loadUrl(imgUrl);
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.log(TAG, responseInfo.result);
                List<PayticketShangyingBean> aattention;
                aattention = new Gson().fromJson(responseInfo.result, PayticketShangyingTopNools.class).getAttention();
                attention.clear();
                attention.addAll(aattention);

                List<PayticketShangyingBean> amoviecomings;
                amoviecomings = new Gson().fromJson(responseInfo.result, PayticketShangyingListNools.class).getMoviecomings();
                moviecomings.clear();
                moviecomings.addAll(amoviecomings);
                listAdapter.notifyDataSetChanged();
                recyadcleApter.notifyDataSetChanged();


                //TODO RecyclerView 监ting
                recyadcleApter.setOnItemClickLitener(new PayticketShangyingRecycleAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String id = attention.get(position).getId();
                        Intent intent = new Intent(getActivity(), ReyingInfoActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("img", attention.get(position).getImage());
                        intent.putExtra("type", attention.get(position).getType());
                        intent.putExtra("name", attention.get(position).getTitle());
                        intent.putExtra("year", attention.get(position).getrYear());
                        intent.putExtra("month", attention.get(position).getrMonth());
                        intent.putExtra("day", attention.get(position).getrDay());
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
            }
            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }


}
