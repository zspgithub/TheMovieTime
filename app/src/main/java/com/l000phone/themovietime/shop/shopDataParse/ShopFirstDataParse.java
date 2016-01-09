package com.l000phone.themovietime.shop.shopDataParse;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;

import com.l000phone.themovietime.shop.network.ShopFirstNetwork;
import com.l000phone.themovietime.shop.shopbean.Category;
import com.l000phone.themovietime.shop.shopbean.CategoryItem;
import com.l000phone.themovietime.shop.shopbean.CellA;
import com.l000phone.themovietime.shop.shopbean.CellB;
import com.l000phone.themovietime.shop.shopbean.CellC;
import com.l000phone.themovietime.shop.shopbean.NavigatorFirthIcon;
import com.l000phone.themovietime.shop.shopbean.NavigatorIcon;
import com.l000phone.themovietime.shop.shopbean.Scroll;
import com.l000phone.themovietime.shop.shopbean.SubList;
import com.l000phone.themovietime.shop.shopbean.Topic;
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


/**获取shop第一界面得到的联网请求数据，并解析返还给调用者
 *  
 */
public class ShopFirstDataParse {

    public static final String path = "http://api.m.mtime.cn/PageSubArea/MarketFirstPageNew.api";

    public static void parse(final Handler handler){

        ShopFirstNetwork.getData(path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {

                    //总的json串
                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                    //解析第一个scollImg
                    JSONArray jsonArray = jsonObject.getJSONArray("scrollImg");

                    List<Scroll> scrolls = JSON.parseArray(jsonArray.toString(), Scroll.class);

                    Message message = new Message();

                    message.what = 1;

                    message.obj = scrolls;

                    handler.sendMessage(message);

                    //解析第二个navigatorIcon

                    JSONArray jsonArray2 = jsonObject.getJSONArray("navigatorIcon");

                    List<NavigatorIcon> navigatorIcons = JSON.parseArray(jsonArray2.toString(), NavigatorIcon.class);

                    Message message2 = new Message();

                    message2.what = 2;

                    message2.obj = navigatorIcons;

                    handler.sendMessage(message2);

                    //解析第三个navigatorFirthIcon

                    JSONObject jsonObject3 = jsonObject.getJSONObject("navigatorFirthIcon");

                    NavigatorFirthIcon navigatorFirthIcon = JSON.parseObject(jsonObject3.toString(), NavigatorFirthIcon.class);

                    Message message3 = new Message();

                    message3.what = 3;

                    message3.obj = navigatorFirthIcon;

                    handler.sendMessage(message3);

                    //解析第四个CellA
                    JSONObject jsonObject4 = jsonObject.getJSONObject("cellA");

                    CellA cellA = JSON.parseObject(jsonObject4.toString(), CellA.class);

                    Message message4 = new Message();

                    message4.what = 4;

                    message4.obj = cellA;

                    handler.sendMessage(message4);

                    //解析第五个cellB
                    JSONObject jsonObject5 = jsonObject.getJSONObject("cellB");

                    CellB cellB = JSON.parseObject(jsonObject5.toString(), CellB.class);

                    Message message5 = new Message();

                    message5.what = 5;

                    message5.obj = cellB;

                    handler.sendMessage(message5);

                    //解析第6个cellC
                    JSONObject jsonObject6 = jsonObject.getJSONObject("cellC");

                    JSONObject jsonObject601 = new JSONObject(jsonObject6.toString());

                    JSONArray jsonArray602 = jsonObject601.getJSONArray("list");

                    List<CellC> cellCs = JSON.parseArray(jsonArray602.toString(), CellC.class);

                    Message message6 = new Message();

                    message6.what = 6;

                    message6.obj = cellCs;

                    handler.sendMessage(message6);

                    //解析第7个topic
                    JSONArray jsonArray7 = jsonObject.getJSONArray("topic");


                    List<Topic> topics = JSON.parseArray(jsonArray7.toString(), Topic.class);


                    Map<String, Object> map7;

                    List<Map<String, Object>> listMap7 = new ArrayList<Map<String, Object>>();

                    for (int i = 0; i < topics.size(); i++) {
                        String subList = topics.get(i).getSubList();

                        JSONArray jsonArray701 = new JSONArray(subList);

                        map7 = new HashMap<String, Object>();

                        List<SubList> subLists = JSON.parseArray(jsonArray701.toString(), SubList.class);

                        map7.put("sublist", subLists);

                        listMap7.add(map7);
                    }

                    Map<String, Object> map7001 = new HashMap<String, Object>();
                    map7001.put("topic", listMap7);
                    Map<String, Object> map7002 = new HashMap<String, Object>();
                    map7002.put("topic", topics);
                    List<Map<String, Object>> top = new ArrayList<Map<String, Object>>();
                    top.add(map7002);
                    top.add(map7001);

                    Message message7 = new Message();

                    message7.what = 7;

                    message7.obj = top;

                    handler.sendMessage(message7);
                    //解析第8个category
                    JSONArray jsonArray8 = jsonObject.getJSONArray("category");

                    List<Category> categories = JSON.parseArray(jsonArray8.toString(), Category.class);

                    Map<String, Object> map;
                    List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
                    for (int i = 0; i < 4; i++) {
                        map = new HashMap<String, Object>();
                        String subList = categories.get(i).getSubList();
                        JSONArray jsonArray801 = new JSONArray(subList);
                        List<CategoryItem> categoryItems = JSON.parseArray(jsonArray801.toString(), CategoryItem.class);
                        map.put("item", categoryItems);
                        mapList.add(map);
                    }
                    Map<String, Object> map8001 = new HashMap<String, Object>();
                    map8001.put("category", categories);
                    Map<String, Object> map8002 = new HashMap<String, Object>();
                    map8002.put("category", mapList);
                    List<Map<String, Object>> cat = new ArrayList<Map<String, Object>>();
                    cat.add(map8001);
                    cat.add(map8002);
                    Message message8 = new Message();

                    message8.what = 8;

                    message8.obj = cat;

                    handler.sendMessage(message8);


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
