package com.l000phone.themovietime.discover.json;

import com.l000phone.themovietime.discover.bean.TopListDetailsBean1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-11-19.
 */
public class TopListDetailsJson1 {
    public static List<TopListDetailsBean1>getJson(String jsonString){
        List<TopListDetailsBean1>list=new ArrayList<TopListDetailsBean1>();
        try {
            JSONObject object=new JSONObject(jsonString);
            JSONArray array=object.getJSONArray("movies");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1=array.getJSONObject(i);
                if (object1!=null){
                    TopListDetailsBean1 bean1=new TopListDetailsBean1();
                    bean1.setActor(object1.getString("actor"));
                    bean1.setDirector(object1.getString("director"));
                    bean1.setId(object1.getString("id"));
                    bean1.setMovieType(object1.getString("movieType"));
                    bean1.setName(object1.getString("name"));
                    bean1.setNameEn(object1.getString("nameEn"));
                    bean1.setPosterUrl(object1.getString("posterUrl"));
                    bean1.setRankNum(object1.getString("rankNum"));
                    bean1.setRating(object1.getString("rating"));
                    bean1.setReleaseDate(object1.getString("releaseDate"));
                    bean1.setReleaseLocation(object1.getString("releaseLocation"));
                    bean1.setRemark(object1.getString("remark"));
                    list.add(bean1);
                }

            }
            return list;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
