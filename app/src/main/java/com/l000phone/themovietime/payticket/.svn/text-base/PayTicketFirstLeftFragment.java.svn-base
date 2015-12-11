package com.l000phone.themovietime.payticket;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.l000phone.themovietime.MainActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.adapters.PayTicketReyingAdapter;
import com.l000phone.themovietime.payticket.bean.PayticketReyingBean;
import com.l000phone.themovietime.payticket.bean.PayticketReyingNools;
import com.l000phone.themovietime.utils.LogUtil;
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
public class PayTicketFirstLeftFragment extends Fragment {

    private List<PayticketReyingBean> list = new ArrayList<>();
    private String url = "http://api.m.mtime.cn/Showtime/LocationMovies.api?locationId=290";
    private MainActivity activity;
    private ListView listView;

    public PayTicketFirstLeftFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = ((MainActivity) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_ticket_first_left, container, false);
        //new PayTicketReyingTask(getContext(),this).execute("http://api.m.mtime.cn/Showtime/LocationMovies.api?locationId=290");
//        new PayTicketReyingTask(getContext(),this).execute(url);
//        LogUtil.log("oooo", "0000000");
        initView(view);
        return view;
    }
    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.payticket_reying_listview);
        final PayTicketReyingAdapter adapter = new PayTicketReyingAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.result!=null) {
                    List<PayticketReyingBean> alist;
                    alist = new Gson().fromJson(responseInfo.result, PayticketReyingNools.class).getMs();
                    list.clear();
                    list.addAll(alist);
                    adapter.notifyDataSetChanged();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //TODO 点击传值  传id
                            String Id = list.get(position).getId();
                            String showtime = list.get(position).getRd();
                            Intent intent = new Intent(getActivity(), ReyingInfoActivity.class);
                            intent.putExtra("id", Id);
                            intent.putExtra("name",list.get(position).getT());
                            intent.putExtra("img",list.get(position).getImg());
                            intent.putExtra("type", list.get(position).getMovieType());
                            intent.putExtra("time",list.get(position).getD());
                            intent.putExtra("year",showtime.substring(0, 4));
                            intent.putExtra("month",showtime.substring(4, 6));
                            intent.putExtra("day",showtime.substring(6));
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
            }
        });

    }

}
