package com.l000phone.themovietime.shop.network;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**shop第一界面联网请求的类
 * 
 */
public class ShopFirstNetwork {

    public static void getData(String url ,RequestCallBack<String> requestCallBack){

        HttpUtils httpUtils = new HttpUtils(5000);

        RequestParams params = new RequestParams();

        params.addBodyParameter("lastTime","0");

        httpUtils.send(HttpRequest.HttpMethod.POST,url,params,requestCallBack);
    }

    public static void getBottomData(String url ,RequestCallBack<String> requestCallBack){

        HttpUtils httpUtils = new HttpUtils(5000);

        httpUtils.send(HttpRequest.HttpMethod.POST,url,requestCallBack);
    }

    public static void getPriceData(String url ,String id,RequestCallBack<String> requestCallBack){

        HttpUtils httpUtils = new HttpUtils(5000);

        RequestParams params = new RequestParams();

        params.addBodyParameter("goodsId",id);

        httpUtils.send(HttpRequest.HttpMethod.POST,url,params,requestCallBack);
    }

    public static void getShopBuyData(String url ,String id,String page,RequestCallBack<String> requestCallBack){

        HttpUtils httpUtils = new HttpUtils(5000);

        RequestParams params = new RequestParams();

        params.addBodyParameter("goodsId",id);

        params.addBodyParameter("pageIndex",page);

        httpUtils.send(HttpRequest.HttpMethod.POST,url,params,requestCallBack);
    }

    public static void getShopBuyFirstData(String url ,String id,RequestCallBack<String> requestCallBack){

        HttpUtils httpUtils = new HttpUtils(5000);

        RequestParams params = new RequestParams();

        params.addBodyParameter("goodsId", id);

        params.addBodyParameter("t", getSystemDataATime());

        httpUtils.send(HttpRequest.HttpMethod.POST,url,params,requestCallBack);
    }

    public static void getLotsGridData(String url ,String name,String pageIndex,String categoryId1,RequestCallBack<String> requestCallBack){

        HttpUtils httpUtils = new HttpUtils(5000);

        RequestParams params = new RequestParams();

        params.addBodyParameter("keyword", name);

        params.addBodyParameter("pageIndex",pageIndex);

        params.addBodyParameter("sm","2");
        params.addBodyParameter("categoryId1",categoryId1);

        params.addBodyParameter("t", getSystemDataATime());

        httpUtils.send(HttpRequest.HttpMethod.POST,url,params,requestCallBack);
    }

    public static void getviewpagerData(String url,String param,RequestCallBack<String> requestCallBack){

        HttpUtils httpUtils = new HttpUtils(5000);

        RequestParams params = new RequestParams();

        params.addBodyParameter("scanCode", param);

        httpUtils.send(HttpRequest.HttpMethod.POST,url,params,requestCallBack);
    }

    public static void getWebData(String url,RequestCallBack<String> requestCallBack){

        HttpUtils httpUtils = new HttpUtils(5000);

        httpUtils.send(HttpRequest.HttpMethod.GET,url,requestCallBack);
    }
    public static String getSystemDataATime() {
        //24小时制
        long currentTimeMillis = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(currentTimeMillis);

        Date date = new Date(currentTimeMillis);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHmmssSSSSS");

        return simpleDateFormat.format(date);
    }
}
