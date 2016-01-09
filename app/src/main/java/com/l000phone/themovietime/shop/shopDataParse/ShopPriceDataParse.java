package com.l000phone.themovietime.shop.shopDataParse;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;

import com.l000phone.themovietime.shop.network.ShopFirstNetwork;
import com.l000phone.themovietime.shop.shopbean.Price;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *  
 */
public class ShopPriceDataParse {

    public static final String urlprice = "http://mall.wv.mtime.cn/Service/callback.mi/ECommerce/GoodsInfo.api";

    public static void parse(String id,final Handler handler){

        ShopFirstNetwork.getPriceData(urlprice, id, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONObject jsonObject1 = jsonObject.getJSONObject("goods");

                    Price price = JSON.parseObject(jsonObject1.toString(), Price.class);

                    Message message = new Message();

                    message.what = 21;

                    message.obj = price;

                    handler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    public static void parse2(String id,final Handler handler){

        ShopFirstNetwork.getPriceData(urlprice, id, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONObject jsonObject1 = jsonObject.getJSONObject("goods");

                    Price price = JSON.parseObject(jsonObject1.toString(), Price.class);

                    Message message = new Message();

                    message.what = 22;

                    message.obj = price;

                    handler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    public static void parse3(String id,final Handler handler){

        ShopFirstNetwork.getPriceData(urlprice, id, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONObject jsonObject1 = jsonObject.getJSONObject("goods");

                    Price price = JSON.parseObject(jsonObject1.toString(), Price.class);

                    Message message = new Message();

                    message.what = 23;

                    message.obj = price;

                    handler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    public static void parse4(String id,final Handler handler){

        ShopFirstNetwork.getPriceData(urlprice, id, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);


                    JSONObject jsonObject1 = jsonObject.getJSONObject("goods");

                    Price price = JSON.parseObject(jsonObject1.toString(), Price.class);

                    Message message = new Message();

                    message.what = 24;

                    message.obj = price;

                    handler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
