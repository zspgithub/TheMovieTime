package com.l000phone.themovietime.utils;

import android.os.AsyncTask;

import com.l000phone.themovietime.firstpage.bean.CityBean;
import com.l000phone.themovietime.firstpage.utils.ParserCityBean;

import java.util.List;

/**
 * 城市选择类
 * 该类接收来自定位或者用户点击的城市名，返回URL连接
 *
 * Created by Administrator on 15-11-17.
 */
public class CitySelect {

    //先设置默认城市是北京
    private static String cityName = "北京";
    private static String cityId = "290";
    private static String cityUrl = "http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=";
    private static List<CityBean> list;

    public static String getCityName() {
        return cityName;
    }

    public static void setCityName(String name) {
        cityName = name;
    }

    public static void setCityId(String id) {
        cityId = id;
    }

    public static String getUrl(){

        for (int i=0;i<list.size();i++){
            if (getCityName().equals(list.get(i).getN())){
                cityId = list.get(i).getId();
            }
        }
        LogUtil.log("city","城市名字"+getCityName());
        LogUtil.log("city","城市id"+cityId);
        return cityUrl+cityId;
    }

    public static void setList(List<CityBean> cityBeans) {
        list = cityBeans;
    }

    public static List<CityBean> getList() {
        return list;
    }

    public static String getCityId() {
        return cityId;
    }
}
