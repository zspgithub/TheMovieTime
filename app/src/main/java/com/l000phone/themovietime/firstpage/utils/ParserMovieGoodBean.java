package com.l000phone.themovietime.firstpage.utils;

import com.l000phone.themovietime.firstpage.bean.MoviesGoodBean;
import com.l000phone.themovietime.firstpage.bean.MoviesGoodDetialBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 最下边每日佳片解析类
 *
 * Created by Administrator on 15-11-16.
 */
public class ParserMovieGoodBean {

    public static MoviesGoodBean getMovieGood(String json){

        MoviesGoodBean moviesGoodBean = new MoviesGoodBean();

        try {
            JSONObject object = new JSONObject(json);
            JSONObject object1 = object.getJSONObject("hotMovie");

            JSONObject object2 = object1.optJSONObject("movie");
            MoviesGoodDetialBean detialBean = new MoviesGoodDetialBean();
            detialBean.setDesc(object2.optString("desc"));
            detialBean.setImage(object2.optString("image"));
            detialBean.setMovieId(object2.optString("movieId"));
            detialBean.setTitleCn(object2.optString("titleCn"));
            detialBean.setTitleEn(object2.optString("titleEn"));
            detialBean.setYear(object2.optString("year"));

            moviesGoodBean.setMovie(detialBean);
            moviesGoodBean.setTitle(object1.optString("title"));
            moviesGoodBean.setNewsId(object1.optString("newsId"));
            moviesGoodBean.setTopCover(object1.optString("topCover"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesGoodBean;
    }

}
