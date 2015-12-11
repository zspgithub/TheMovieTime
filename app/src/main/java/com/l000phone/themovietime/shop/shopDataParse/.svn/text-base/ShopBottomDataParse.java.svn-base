package com.l000phone.themovietime.shop.shopDataParse;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;

import com.l000phone.themovietime.shop.network.ShopFirstNetwork;
import com.l000phone.themovietime.shop.shopbean.GoodsList;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by bfc on 2015/11/17.
 */
public class ShopBottomDataParse {

    public static final String url = "http://api.m.mtime.cn/ECommerce/RecommendProducts.api?pageIndex=1&goodsIds=100812%2C100511%2C101457";

    public static void parse(final Handler handler){

        ShopFirstNetwork.getBottomData(url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {

                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONArray jsonArray1 = jsonObject.getJSONArray("goodsList");

                    List<GoodsList> goodsLists = JSON.parseArray(jsonArray1.toString(), GoodsList.class);

                    Message message = new Message();

                    message.what = 1;

                    message.obj = goodsLists;

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
