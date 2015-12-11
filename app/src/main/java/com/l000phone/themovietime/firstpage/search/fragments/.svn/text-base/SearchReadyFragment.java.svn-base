package com.l000phone.themovietime.firstpage.search.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.search.asynctask.SearchHotAsyncTask;
import com.l000phone.themovietime.utils.CitySelect;
import com.l000phone.themovietime.utils.ImageUtils;
import com.l000phone.themovietime.firstpage.search.asynctask.SearchHotAsyncTask.HotSearchCallback;
import com.l000phone.themovietime.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchReadyFragment extends Fragment implements HotSearchCallback {

    //图片网址
    private String imgpath = "http://api.m.mtime.cn/Advertisement/MobileAdvertisementInfo.api?locationId=" + CitySelect.getCityId();
    private String hot = "http://api.m.mtime.cn/Search/HotKeyWords.api";

    private View view;
    private ImageView search_raedy_img;
    private ViewGroup search_ready_container;
    private ListView search_history_list;
    private ImageUtils imageUtils;
    private TextView textView;
    private TextView search_history_clear;
    private TextView search_history_tv;

    public SearchReadyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.first_search_ready, container, false);
        textView = (TextView) inflater.inflate(R.layout.first_search_hot_tv, null);

        initView();

        LogUtil.log("search", "图片路径:" + imgpath);
        imageUtils = new ImageUtils();
        imageUtils.loadImage(imgpath, search_raedy_img);

        new SearchHotAsyncTask(getContext(), this).execute(hot);

        //搜索历史
        SharedPreferences sp = getActivity().getSharedPreferences("search", Context.MODE_PRIVATE);
        HashSet<String> history = (HashSet<String>) sp.getStringSet("history", null);
        List<String> list = new ArrayList<>();
        if (history!=null &&   history.size()>0){
            list.addAll(history);
            LogUtil.log("search", "搜索记录不为空");
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,list);
            search_history_list.setAdapter(arrayAdapter);
            search_history_list.setVisibility(View.VISIBLE);
            search_history_clear.setVisibility(View.VISIBLE);
            search_history_tv.setVisibility(View.VISIBLE);
            search_history_list.addFooterView(search_history_clear);
        }else{
            search_history_clear.setVisibility(View.GONE);
            search_history_list.setVisibility(View.GONE);
            search_history_tv.setVisibility(View.GONE);
        }

        return view;
    }

    //初始化布局
    private void initView() {

        search_raedy_img = (ImageView) view.findViewById(R.id.search_raedy_img);
        search_ready_container = (LinearLayout) view.findViewById(R.id.search_ready_container);
        search_history_list = (ListView) view.findViewById(R.id.search_history_list);
        search_history_clear = (TextView) view.findViewById(R.id.search_history_clear);
        search_history_tv = (TextView) view.findViewById(R.id.search_history_tv);

        search_history_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getActivity().getSharedPreferences("search", Context.MODE_PRIVATE);
                Set<String> history = sp.getStringSet("history", null);
                history.clear();
                SharedPreferences.Editor editor = sp.edit();
                editor.putStringSet("history",history);
                editor.commit();
                search_history_list.setVisibility(View.GONE);
                search_history_clear.setVisibility(View.GONE);
                search_history_tv.setVisibility(View.GONE);
            }
        });

    }


    //热门搜索回调类
    @Override
    public void getHotSearch(String[] keywords) {

        int itemMargin = 50;
        int lineMargin = 50;

        int containerWidth = search_ready_container.getMeasuredWidth() - search_ready_container.getPaddingLeft() - search_ready_container.getPaddingRight();
        int itemPadding = textView.getCompoundPaddingLeft() + textView.getCompoundPaddingRight();

        LayoutInflater inflater = LayoutInflater.from(getContext());

        Paint paint = new Paint();
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvParams.setMargins(0, 0, itemMargin, 0);
        TextView tv = (TextView) inflater.inflate(R.layout.first_search_hot_tv,null);
        paint.setTextSize(tv.getTextSize());

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        search_ready_container.addView(linearLayout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, lineMargin, 0, 0);

        int remainWidth = containerWidth;//每一行剩下的宽度

        for (int i = 0; i < keywords.length; i++) {

            String text = keywords[i];
            LogUtil.log("search",text);
            float itemWidth = paint.measureText(text) + itemPadding;
            LogUtil.log("search", "剩余宽度：" + remainWidth);
            LogUtil.log("search", "TextView宽度" + itemWidth);

            TextView tv1 = (TextView) inflater.inflate(R.layout.first_search_hot_tv, null);
            if (remainWidth > itemWidth) {
                tv1.setText(text);
                linearLayout.addView(tv1, tvParams);
            } else {
                TextView preTextView = (TextView) linearLayout.getChildAt(linearLayout.getChildCount()-1);
                preTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setLayoutParams(params);
                tv1.setText(text);
                linearLayout.addView(tv1, tvParams);
                search_ready_container.addView(linearLayout);
                remainWidth = containerWidth;
            }
            remainWidth = (int) ((remainWidth - itemWidth +0.5f) - itemMargin);

            //点击事件
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //此时应该携带Text跳转到searchresult界面
                }
            });

        }

    }

}
