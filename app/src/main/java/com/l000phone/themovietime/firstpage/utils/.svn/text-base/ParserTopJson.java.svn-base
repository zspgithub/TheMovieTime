package com.l000phone.themovietime.firstpage.utils;

import android.graphics.Movie;

import com.alibaba.fastjson.JSON;
import com.l000phone.themovietime.firstpage.bean.FirstMovieBean;
import com.l000phone.themovietime.firstpage.bean.FirstNearstShowtimeBean;
import com.l000phone.themovietime.firstpage.bean.FirstTopBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 首界面Json解析类
 *
 * Created by Administrator on 15-11-14.
 */
public class ParserTopJson {

    //最外层：FirstTopBean
    public static FirstTopBean getFirstTopBean(String json){
        FirstTopBean firstTopBean = null;
        try {
            JSONObject object = new JSONObject(json);
            firstTopBean = JSON.parseObject(object.toString(),FirstTopBean.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return firstTopBean;
    }

    //第二层：movie
    public static List<FirstMovieBean> getMovies(String json){

        List<FirstMovieBean> movies = null;

        try {
            JSONObject object = new JSONObject(json);

            JSONArray array = object.getJSONArray("movies");
            movies = JSON.parseArray(array.toString(),FirstMovieBean.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    //第三层：
    public static FirstNearstShowtimeBean getFirstNearstBean(String json){

        FirstNearstShowtimeBean firstNearsBean = null;

        try {
            JSONObject object = new JSONObject(json);

            JSONArray array = object.getJSONArray("movies");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return firstNearsBean;
    }

}
