package com.l000phone.themovietime.payticket.utils;

import com.l000phone.themovietime.payticket.bean.PayticketYingyuanBean;
import com.l000phone.themovietime.payticket.bean.PayticketYingyuanFeatureBean;
import com.l000phone.themovietime.payticket.bean.PayticketYingyuanFirstBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class PayticketYingyuanJson {
    public List<PayticketYingyuanBean> getData(String json){
        List<PayticketYingyuanBean> data = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                PayticketYingyuanBean yingyuanBean = new PayticketYingyuanBean();
                JSONObject object = array.getJSONObject(i);
                JSONObject object1  =object.getJSONObject("feature");
                JSONArray array1 = object.getJSONArray("couponActivityList");
                yingyuanBean.setAddress(object.optString("address"));
                yingyuanBean.setCinameName(object.optString("cinameName"));
                yingyuanBean.setMinPrice(object.optString("minPrice"));
                yingyuanBean.setCinemaId(object.optString("cinemaId"));

                PayticketYingyuanFeatureBean featureBean = new PayticketYingyuanFeatureBean();
                featureBean.setHasIMAX(object1.optString("hasIMAX"));
                featureBean.setHasPark(object1.optString("hasPark"));
                featureBean.setHasVIP(object1.optString("hasVIP"));
                featureBean.setHasWifi(object1.optString("hasWifi"));
                yingyuanBean.setFeature(featureBean);
                List<PayticketYingyuanFirstBean> list = new ArrayList<>();
                if (array1!=null&&array.length()!=0){
                    for (int j = 0; j < array1.length(); j++) {
                        PayticketYingyuanFirstBean firstBean = new PayticketYingyuanFirstBean();
                        JSONObject object2  = array1.getJSONObject(j);
                        firstBean.setId(object2.optString("id"));
                        firstBean.setTag(object2.optString("tag"));
                        list.add(firstBean);
                    }
                    yingyuanBean.setCouponActivityList(list);
                }
                data.add(yingyuanBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
