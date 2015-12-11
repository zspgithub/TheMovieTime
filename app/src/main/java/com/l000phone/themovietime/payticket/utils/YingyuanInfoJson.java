package com.l000phone.themovietime.payticket.utils;

import com.l000phone.themovietime.payticket.bean.YingyuanInfoBean;
import com.l000phone.themovietime.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class YingyuanInfoJson {
    public List<YingyuanInfoBean> getData(String json){
        List<YingyuanInfoBean> list = new ArrayList<>();
        try {

            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("movies");
            for (int i = 0; i < array.length(); i++) {
                YingyuanInfoBean infoBean = new YingyuanInfoBean();
                JSONObject object1 = array.getJSONObject(i);
                infoBean.setTitle(object1.optString("title"));
                infoBean.setImg(object1.optString("img"));
                infoBean.setLength(object1.optString("length"));
                infoBean.setType(object1.optString("type"));
                JSONArray array1 = object1.getJSONArray("showDates");
                String [] data = new String[array1.length()];
                for (int j = 0; j < array1.length(); j++) {
                    data[j] = array1.getString(j);
                }
                infoBean.setShowDates(data);
                list.add(infoBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
