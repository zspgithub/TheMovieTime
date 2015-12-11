package com.l000phone.themovietime.payticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.adapters.YingyuanInfoPiaoAdapter;
import com.l000phone.themovietime.payticket.adapters.YingyuanRecyclerAdapter;
import com.l000phone.themovietime.payticket.bean.PayticketYingyuanBean;
import com.l000phone.themovietime.payticket.bean.YingyuanInfoBean;
import com.l000phone.themovietime.payticket.bean.YingyuanInfoOutBean;
import com.l000phone.themovietime.payticket.bean.YingyuanInfoPiaoBean;
import com.l000phone.themovietime.payticket.bean.YingyuanInfoPiaoNools;
import com.l000phone.themovietime.payticket.utils.YingyuanInfoJson;
import com.l000phone.themovietime.utils.HttpUtil;
import com.l000phone.themovietime.utils.LogUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YingyuanInfoActivity extends AppCompatActivity {
    private String url = "http://api.m.mtime.cn/Showtime/ShowtimeMovieAndDateListByCinema.api?cinemaId=";
    private String infoUrl;
    private TextView yingyuanName;
    private TextView yingyuanAddress;
    private RecyclerView recyclerView;
    private ListView listView;
    private List<YingyuanInfoBean> infoList = new ArrayList<>();
    private TextView movieName;
    private TextView typeTime;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private TextView leftLine;
    private TextView rightLine;
    private String tingURL = "http://api.m.mtime.cn/Showtime/ShowTimesByCinemaMovieDate.api?cinemaId=";
    private String date;
    private String movieId;
    private String id;
    private List<YingyuanInfoPiaoBean> s;
    private YingyuanRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yingyuan_info);
        Intent intent = getIntent();
        id = intent.getStringExtra("cinemaId");
        LogUtil.log("fffff","---"+ id);
        infoUrl = url+ id;
        LogUtil.log("fffff", infoUrl);
        initData();
        initView();
        initTingData();
    }

    private void initData() {
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, infoUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                YingyuanInfoOutBean outBean  =new YingyuanInfoOutBean();
                try {
                    JSONObject object = new JSONObject(responseInfo.result);
                    JSONObject object1 = object.getJSONObject("cinema");
                    outBean.setName(object1.optString("name"));
                    outBean.setAddress(object1.optString("address"));
                    yingyuanName.setText(outBean.getName());
                    yingyuanAddress.setText(outBean.getAddress());
                    LogUtil.log("fffff",outBean.getAddress());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final YingyuanInfoJson infoJson = new YingyuanInfoJson();
                List<YingyuanInfoBean> ainfoList;
                ainfoList = infoJson.getData(responseInfo.result);
                infoList.clear();
                infoList.addAll(ainfoList);
                recyclerAdapter.notifyDataSetChanged();
                recyclerAdapter.setOnItemClickLitener(new YingyuanRecyclerAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        date = infoList.get(position).getShowDates()[0];
                        movieId = infoList.get(position).getMovieId();
                        movieName.setText(infoList.get(position).getTitle());
                        LogUtil.log("ppppp", "--" + position);
                        typeTime.setText(infoList.get(position).getLength() + "-" + infoList.get(position).getType());
                        if (infoList.get(position).getShowDates().length == 2) {
                            radioButton1.setText("今天(" + infoList.get(position).getShowDates()[0] + ")");
                            radioButton2.setText("明天(" + infoList.get(position).getShowDates()[1] + ")");
                            radioButton1.setChecked(true);
                        } else if (infoList.get(position).getShowDates().length == 1) {
                            leftLine.setBackgroundResource(R.color.royalblue);
                            rightLine.setBackgroundResource(R.color.royalblue);
                            radioButton2.setVisibility(View.GONE);
                            if (infoList.get(position).getShowDates()[0].equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
                                radioButton1.setText("今天(" + infoList.get(position).getShowDates()[0] + ")");
                            } else {
                                radioButton1.setText("明天(" + infoList.get(position).getShowDates()[0] + ")");
                            }
                        }
                    }
                });
//                List<String> list = new ArrayList<String>();
//                for (int i = 0; i < 10; i++) {
//                    list.add(""+i);
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(YingyuanInfoActivity.this,android.R.layout.simple_list_item_1,list);
//                listView.setAdapter(adapter);
            }
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void initView() {
        View view = LayoutInflater.from(this).inflate(R.layout.yingyuan_info_top,null);
        yingyuanName = (TextView) view.findViewById(R.id.yingyuan_info_name);
        yingyuanAddress = (TextView) view.findViewById(R.id.yingyuan_info_address);
        movieName = (TextView) view.findViewById(R.id.yingyuan_info_movieName);
        typeTime = (TextView) view.findViewById(R.id.yingyuan_info_movie_timetype);
        recyclerView = (RecyclerView) view.findViewById(R.id.yingyuan_info_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new YingyuanRecyclerAdapter(infoList,YingyuanInfoActivity.this);
        recyclerView.setAdapter(recyclerAdapter);
        listView = (ListView) findViewById(R.id.yingyuan_info_listview);
        listView.addHeaderView(view);
        radioGroup = (RadioGroup) view.findViewById(R.id.yingyuan_info_radioGroup);
        radioButton1 = (RadioButton) view.findViewById(R.id.yingyuan_info_rab1);
        radioButton2 = (RadioButton) view.findViewById(R.id.yingyuan_info_rab2);
        leftLine = (TextView) view.findViewById(R.id.yingyuan_info_blue_line1);
        rightLine = (TextView) view.findViewById(R.id.yingyuan_info_blue_line2);
    }
    private void initTingData(){
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, tingURL+id+"&movieId="+movieId+"&date="+date, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                s = new Gson().fromJson(responseInfo.result, YingyuanInfoPiaoNools.class).getS();
                YingyuanInfoPiaoAdapter adapter = new YingyuanInfoPiaoAdapter(s,YingyuanInfoActivity.this);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
