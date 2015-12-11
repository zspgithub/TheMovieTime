package com.l000phone.themovietime.firstpage.utils;

import com.l000phone.themovietime.firstpage.bean.CityBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市解析类
 *
 * Created by Administrator on 15-11-17.
 */
public class ParserCityBean {

    public static List<CityBean> getCities(String json){
        List<CityBean> list = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);

            JSONArray array = object.getJSONArray("p");
            for (int i=0;i<array.length();i++){

                JSONObject object1 = array.getJSONObject(i);
                CityBean cityBean = new CityBean();
                cityBean.setCount(object1.optString("count"));
                cityBean.setId(object1.optString("id"));
                cityBean.setN(object1.optString("n"));
                cityBean.setPinyinFull(object1.optString("pinyinFull"));
                cityBean.setPinyinShort(object1.optString("pinyinShort"));
                list.add(cityBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

}
