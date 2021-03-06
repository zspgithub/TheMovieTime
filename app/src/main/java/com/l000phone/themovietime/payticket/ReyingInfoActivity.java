package com.l000phone.themovietime.payticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.adapters.ReyingInfoRecycleViewAdapter;
import com.l000phone.themovietime.payticket.bean.ReyingInfoActorBean;
import com.l000phone.themovietime.payticket.bean.ReyingInfoActorNools;
import com.l000phone.themovietime.payticket.bean.ReyingInfoTopBean;
import com.l000phone.themovietime.payticket.bean.ReyingInfoYingpingBean;
import com.l000phone.themovietime.payticket.bean.ReyingInfoYingpingNools;
import com.l000phone.themovietime.utils.HttpUtil;
import com.l000phone.themovietime.utils.ImageUtils;
import com.l000phone.themovietime.utils.LogUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ReyingInfoActivity extends BaseActivity {

    private ImageView movieIcon;
    private TextView movieName;
    private TextView movietime;
    private TextView movieType;
    private TextView movieShow;
    private RecyclerView recyclerView;
    private TextView juqing;
    private String url = "http://api.m.mtime.cn/Showtime/MovieDetail.api?locationId=290&movieId=";
    private String movieUrl;
    private String type;
    private ImageUtils imageUtils = new ImageUtils();
    private String pinglunUrl = "http://api.m.mtime.cn/Movie/extendMovieDetail.api?MovieId=";
    private String path;
    private TextView newTitle;
    private TextView newsContent;
    private ImageView newsImage;
    private TextView yingping;
    private TextView yingpingTitle;
    private TextView yingpingContent;
    private ImageView userImage;
    private TextView userName;
    private TextView yingpingTime;
    private TextView newsContenttitle;
    private String yingpingUrl = "http://api.m.mtime.cn/Movie/HotLongComments.api";
    private List<ReyingInfoYingpingBean> yingpingBeans;
    private String imgUrl;
    private String name;
    private String time;
    private String year;
    private String month;
    private String day;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reying_info);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
        imgUrl = intent.getStringExtra("img");
        name = intent.getStringExtra("name");
        time = intent.getStringExtra("time");
        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        day = intent.getStringExtra("day");
        movieUrl = url+ id;
        path = pinglunUrl+ id;
        initYingpingData();
//        initData();
        initView();
    }

    private void initView() {
        ImageView imageBack = (ImageView) findViewById(R.id.reying_info_image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReyingInfoActivity.this.finish();
            }
        });
        movieIcon = (ImageView) findViewById(R.id.reying_info_movieIcon);
        imageUtils.loadImage(imgUrl, movieIcon);
        movieName = (TextView) findViewById(R.id.reying_info_movieName);
        movieName.setText(name);
        movietime = (TextView) findViewById(R.id.reying_info_movietime);
        movietime.setText(time);
        movieType = (TextView) findViewById(R.id.reying_info_movietype);
        movieType.setText(type);
        movieShow = (TextView) findViewById(R.id.reying_info_movieShowtime);
        movieShow.setText(year+"年"+month+"月"+day+"日");

        newTitle = (TextView) findViewById(R.id.reying_info_news_title);
        newsContent = (TextView) findViewById(R.id.reying_info_news_content);
        newsContenttitle = (TextView) findViewById(R.id.reying_info_news_content_title);
        newsImage = (ImageView) findViewById(R.id.reying_info_news_image);
        yingping = (TextView) findViewById(R.id.reying_info_yingping);
        yingpingTitle = (TextView) findViewById(R.id.reying_info_yingping_title);
        yingpingContent = (TextView) findViewById(R.id.reying_info_yingping_content);
        userImage = (ImageView) findViewById(R.id.reying_info_yingping_userIcon);
        userName = (TextView) findViewById(R.id.reying_info_yingping_userName);
        yingpingTime = (TextView) findViewById(R.id.reying_info_yingping_time);
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject object = new JSONObject(responseInfo.result);
                    JSONArray array = object.getJSONArray("news");
                    JSONObject object1 = array.getJSONObject(0);
                    newTitle.setText(object1.optString("newCount")+"条相关新闻");
                    newsContent.setText(object1.optString("title2"));
                    newsContenttitle.setText(object1.optString("title"));
                    imageUtils.loadImage(object1.optString("image"), newsImage);
                    JSONObject object2 = object.getJSONObject("events");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });


//        juqing = (TextView) findViewById(R.id.reying_info_moviejuqing);
//        recyclerView = (RecyclerView) findViewById(R.id.reying_info_recycleView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);
    }
    private void initYingpingData(){
        HttpUtils httpUtils = new HttpUtils(5000);
        RequestParams params = new RequestParams("UTF-8");
        params.addBodyParameter("movieId",id);
        params.addBodyParameter("pageIndex","1");
        httpUtils.send(HttpRequest.HttpMethod.POST, yingpingUrl, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                yingpingBeans = new Gson().fromJson(responseInfo.result, ReyingInfoYingpingNools.class).getComments();
                if (yingpingBeans!=null&&yingpingBeans.size()!=0) {
                    yingpingTitle.setText(yingpingBeans.get(0).getTitle());
                    yingpingContent.setText(yingpingBeans.get(0).getContent());
                    imageUtils.loadImage(yingpingBeans.get(0).getHeadurl(), userImage);
                    userName.setText(yingpingBeans.get(0).getNickname());
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

   private void initData(){
        HttpUtils httpUtils = new HttpUtils(5000);
        httpUtils.send(HttpRequest.HttpMethod.GET, movieUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtil.log("rrrr","----"+responseInfo.result);
                try {
                    ReyingInfoTopBean topBean = new ReyingInfoTopBean();
                    JSONObject object = new JSONObject(responseInfo.result);
                    topBean.setT(object.optString("t"));
                    topBean.setD(object.optString("d"));
                    topBean.setPt(object.optString("pt"));
                    topBean.setRd(object.optString("rd"));
                    topBean.setImg(object.optString("img"));
                    movieName.setText(topBean.getT());
                    movieShow.setText(topBean.getRd().substring(0, 3) + "年" + topBean.getRd().substring(3, 5) + "月" + topBean.getRd().substring(5) + "日上映");
                    movietime.setText(topBean.getD());
                    juqing.setText("剧情："+topBean.getPt());
                    imageUtils.loadImage(topBean.getImg(),movieIcon);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<ReyingInfoActorBean> actors = new Gson().fromJson(responseInfo.result, ReyingInfoActorNools.class).getActors();
                ReyingInfoRecycleViewAdapter adapter = new ReyingInfoRecycleViewAdapter(actors,ReyingInfoActivity.this);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
