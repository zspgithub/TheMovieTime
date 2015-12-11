package com.l000phone.themovietime.payticket;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.adapters.PayticketYingyuanAdapter;
import com.l000phone.themovietime.payticket.adapters.PayticketYingyuanTehuiAdapter;
import com.l000phone.themovietime.payticket.bean.PayticketShangyingBean;
import com.l000phone.themovietime.payticket.bean.PayticketYingyuanBean;
import com.l000phone.themovietime.payticket.utils.PayticketYingyuanJson;
import com.l000phone.themovietime.utils.LogUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayTicketYingyuanFragment extends Fragment {

    private String url = "http://static1.mtime.cn/feature/mobile/item/2015/banner/1115/mt1115_175.html";
    private RadioGroup radioGroup;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String allUrl = "http://api.m.mtime.cn/OnlineLocationCinema/OnlineCinemasByCity.api?locationId=290&deviceToken=1";
    private String listUrl;
    private List<PayticketYingyuanBean> yingyuanList = new ArrayList<>();
    private List<PayticketYingyuanBean> lists = new ArrayList<>();
    private PayticketYingyuanJson payticketYingyuanJson = new PayticketYingyuanJson();
    private ListView listView;
    private PayticketYingyuanAdapter adapter;

    public PayTicketYingyuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_ticket_yingyuan, container, false);
        listUrl = allUrl;
        initData();
        adapter = new PayticketYingyuanAdapter(lists,getActivity());
        initView(view);

        return view;
    }

    private void initView(View view) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.payticketyingyuanwebview,null);
        WebView webView = (WebView)v.findViewById(R.id.payticket_yingyuan_fragment_webview);
        webView.loadUrl(url);
        listView = (ListView) view.findViewById(R.id.payticket_yingyuan_fragment_listView);

        listView.addHeaderView(v);
        radioGroup = (RadioGroup) view.findViewById(R.id.payticket_yingyuan_fragment_tabRadiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.payticket_yingyuan_fragment_tab1:
//                        adapter = new PayticketYingyuanAdapter(lists, getActivity());
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.payticket_yingyuan_fragment_tab2:
                        PayticketYingyuanTehuiAdapter tehuiAdapter = new PayticketYingyuanTehuiAdapter(lists, getContext());
                        listView.setAdapter(tehuiAdapter);
                        break;
                    case R.id.payticket_yingyuan_fragment_tab3:
                        PayticketYingyuanTehuiAdapter tehuiAdapter1 = new PayticketYingyuanTehuiAdapter(lists, getContext());
                        listView.setAdapter(tehuiAdapter1);
                        break;
                }
            }
        });
    }
    private void initData(){
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, listUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                yingyuanList = payticketYingyuanJson.getData(responseInfo.result);
                lists.addAll(yingyuanList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), YingyuanInfoActivity.class);
                        intent.putExtra("cinemaId", lists.get(position-1).getCinemaId());

                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }
}
