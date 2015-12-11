package com.l000phone.themovietime.shop.shopDataParse;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.l000phone.themovietime.shop.network.ShopFirstNetwork;
import com.l000phone.themovietime.shop.shopbuybean.ContentList;
import com.l000phone.themovietime.shop.shopbuybean.Goods;
import com.l000phone.themovietime.shop.shopbuybean.Goodssss;
import com.l000phone.themovietime.shop.shopbuybean.GotoPage;
import com.l000phone.themovietime.shop.shopbuybean.LotsgoodsList;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bfc on 2015/11/18.
 */
public class ShopBuyDataParse {

    public static final String buyUrl = "http://mall.wv.mtime.cn/Service/callback.mi/ECommerce/GoodsImageTextInfo.api";

    public static void parse(String id, final Handler handler) {

        ShopFirstNetwork.getShopBuyData(buyUrl, id, "1", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONArray jsonArray1 = jsonObject.getJSONArray("contentList");

                    List<ContentList> contentLists = JSON.parseArray(jsonArray1.toString(), ContentList.class);

                    Message message = new Message();

                    message.what = 1;

                    message.obj = contentLists;

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

    public static void parseNext(String id, final Handler handler) {

        ShopFirstNetwork.getShopBuyData(buyUrl, id, "2", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONArray jsonArray1 = jsonObject.getJSONArray("contentList");

                    List<ContentList> contentLists = JSON.parseArray(jsonArray1.toString(), ContentList.class);

                    Message message = new Message();

                    message.what = 2;

                    message.obj = contentLists;

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

    public static final String firstDataParseUrl = "http://mall.wv.mtime.cn/Service/callback.mi/ECommerce/GoodsInfo.api";

    public static void firstDataParse(String id, final Handler handler) {

        ShopFirstNetwork.getShopBuyFirstData(firstDataParseUrl, id, new RequestCallBack<String>() {
            @Override

            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONObject jsonObject1 = jsonObject.getJSONObject("goods");

                    Goods goods301 = JSON.parseObject(jsonObject1.toString(), Goods.class);

                    JSONArray jsonArray1 = jsonObject1.getJSONArray("imageHorizontals");

                    com.alibaba.fastjson.JSONArray liststring = JSON.parseArray(jsonArray1.toString());

                    int size = liststring.size();

                    Map<String, Object> map = new HashMap<String, Object>();

                    if (size == 0) {
                        String image = goods301.getImage();
                        List<String> list2 = new ArrayList<String>();
                        list2.add(image);
                        map.put("listString", list2);
                    } else {
                        map.put("listString", liststring);
                    }

                    map.put("goods", goods301);

                    Message message3 = new Message();

                    message3.what = 3;

                    message3.obj = map;


                    handler.sendMessage(message3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    public static final String gridviewUrl = "http://mall.wv.mtime.cn/Service/callback.mi/ECommerce/RecommendProducts.api/";

    public static void shopbuygridviewParse(String id, final Handler handler) {

        ShopFirstNetwork.getShopBuyFirstData(gridviewUrl, id, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONArray jsonArray1 = jsonObject.getJSONArray("goodsList");

                    List<Goodssss> goodsLists = JSON.parseArray(jsonArray1.toString(), Goodssss.class);

                    Message message = new Message();

                    message.what = 55;

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

    public static final String lotsGridUrl = "http://mall.wv.mtime.cn/Service/callback.mi/ECommerce/SearchGoods.api/";

    public static void lotsGridShopBuy(String name, String pageIndex, String categoryId1,final Handler handler) {

        ShopFirstNetwork.getLotsGridData(lotsGridUrl, name, pageIndex,categoryId1, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONObject jsonObject1 = jsonObject.getJSONObject("content");

                    JSONObject jsonObject2 = jsonObject1.getJSONObject("goods");

                    JSONArray jsonArray1 = jsonObject2.getJSONArray("goodsList");

                    if (jsonArray1 != null) {
                        List<LotsgoodsList> lotsgoodsLists = JSON.parseArray(jsonArray1.toString(), LotsgoodsList.class);

                        Message message = new Message();

                        message.what = 1;

                        message.obj = lotsgoodsLists;

                        handler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }
    public static final String viewPagerUrl = "http://api.m.mtime.cn/Showtime/QRCodeControl.api";

    public static void viewPagerDataParse(String parmas, final Handler handler){
        ShopFirstNetwork.getviewpagerData(viewPagerUrl,parmas,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);

                    JSONObject jsonObject1 = jsonObject.getJSONObject("gotoPage");

                    GotoPage gotoPage = JSON.parseObject(jsonObject1.toString(), GotoPage.class);

                    Message message = new Message();

                    message.what = 1;

                    message.obj = gotoPage;

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

    public static void getWebDataParse(String url, final Handler handler){

        ShopFirstNetwork.getWebData(url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String web = responseInfo.result;

                Message message = new Message();
                message.what = 2;
                message.obj = web;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}

