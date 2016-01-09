package com.l000phone.themovietime.firstpage.utils;

import com.l000phone.themovietime.firstpage.bean.MoviesHotBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页下边
 *
 * 
 */
public class ParserMovieBean {

    /**
     * 今日热点的电影
     *
     * @return
     */
    public static List<MoviesHotBean> getMoviesHotBean(String json){

        List<MoviesHotBean> list = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("hotPoints");
            for (int i=0;i<array.length();i++){

                JSONObject object1 = array.getJSONObject(i);
                MoviesHotBean moviesHotBean = new MoviesHotBean();
                moviesHotBean.setCommentCount(object1.optString("commentCount"));
                moviesHotBean.setDesc(object1.optString("desc"));
                moviesHotBean.setId(object1.optString("id"));
                moviesHotBean.setImg(object1.optString("img"));
                moviesHotBean.setImg2(object1.optString("img2"));
                moviesHotBean.setImg3(object1.optString("img3"));
                moviesHotBean.setPublishTime(object1.optString("publishTime"));
                moviesHotBean.setTag(object1.optString("tag"));
                moviesHotBean.setTitle(object1.optString("title"));
                moviesHotBean.setType(object1.optString("type"));

                list.add(moviesHotBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
