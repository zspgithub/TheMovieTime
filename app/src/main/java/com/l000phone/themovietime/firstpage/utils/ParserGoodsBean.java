package com.l000phone.themovietime.firstpage.utils;

import com.alibaba.fastjson.JSON;
import com.l000phone.themovietime.firstpage.bean.GoodsGotoPageBean;
import com.l000phone.themovietime.firstpage.bean.GoodsPagerBean;
import com.l000phone.themovietime.firstpage.bean.GoodsSubBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 中间商品解析类
 *
 * 
 */
public class ParserGoodsBean {
    /**
     * 商品
     * @param json
     * @return
     */

    private static String[] subStr = new String[]{"subFifth","subFirst","subSecond","subThird"};

    public static List<GoodsSubBean> getGoodsSubBean(String json){

        List<GoodsSubBean> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONObject object1 = object.getJSONObject("areaSecond");

            for (int i=0;i<subStr.length;i++){
                JSONObject object2 = object1.getJSONObject(subStr[i]);

                GoodsSubBean goodsSubBean = new GoodsSubBean();
                goodsSubBean.setGoodsId(object2.optString("goodsId"));
                goodsSubBean.setId(object2.optString("id"));
                goodsSubBean.setImage(object2.optString("image"));
                goodsSubBean.setImage2(object2.optString("image2"));
                goodsSubBean.setTitle(object2.optString("title"));
                goodsSubBean.setTitleColor(object2.optString("titleColor"));
                goodsSubBean.setTitleSmall(object2.optString("titleSmall"));
                list.add(goodsSubBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 商品详情
     *
     * @param json
     * @return
     */
    public static List<GoodsGotoPageBean> getGoodsPageBean(String json){

         List<GoodsGotoPageBean> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONObject object1 = object.getJSONObject("areaSecond");

            for (int i=0;i<subStr.length;i++){
                JSONObject object2 = object1.getJSONObject(subStr[i]);
                JSONObject object3 = object2.getJSONObject("gotoPage");

                GoodsGotoPageBean gotoPageBean = new GoodsGotoPageBean();
                gotoPageBean.setGotoType(object3.optString("gotoType"));
                gotoPageBean.setRelatedTypeUrl(object3.optString("relatedTypeUrl"));
                gotoPageBean.setUrl(object3.optString("url"));
                list.add(gotoPageBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }


    /**
     * 中间定时滑动广告条的解析类
     *
     * @param json
     * @return
     */
    public static List<GoodsPagerBean> getGoodsPagerBeans(String json){

        List<GoodsPagerBean> list = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("advList");
            for (int i=0;i<array.length();i++){
                JSONObject object1 = array.getJSONObject(i);
                GoodsPagerBean goodsPagerBean = new GoodsPagerBean();
                goodsPagerBean.setUrl(object1.optString("url"));
                goodsPagerBean.setImg(object1.optString("img"));
                goodsPagerBean.setImg2(object1.optString("img2"));
                list.add(goodsPagerBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

}
