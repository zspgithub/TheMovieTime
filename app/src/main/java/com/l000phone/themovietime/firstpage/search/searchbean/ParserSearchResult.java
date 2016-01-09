package com.l000phone.themovietime.firstpage.search.searchbean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果解析类
 *
 * 
 */
public class ParserSearchResult {

    public static List<SearchResultBean> getSearchResults(String json){
        List<SearchResultBean> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("movies");
            for (int i=0;i<array.length();i++){
                JSONObject object1 = array.getJSONObject(i);

                SearchResultBean bean = new SearchResultBean();

                JSONArray array1 = object1.getJSONArray("actors");
                String[] actors = new String[array1.length()];
                for (int j=0;j<array1.length();j++){
                    actors[i] = array1.getString(i);
                }
                bean.setActors(actors);

                JSONArray array2 = object1.getJSONArray("directors");
                String[] directors = new String[array2.length()];
                for (int j=0;j<array2.length();j++){
                    directors[i] = array2.getString(i);
                }
                bean.setDirectors(directors);

                bean.setId(object1.optString("id"));
                bean.setImg(object1.optString("img"));
                bean.setLocationName(object1.optString("locationName"));
                bean.setMovieType(object1.optString("movieType"));
                bean.setNameEn(object1.optString("nameEn"));
                bean.setName(object1.optString("name"));
                bean.setRating(object1.optString("rating"));
                bean.setrDay(object1.optString("rDay"));
                bean.setRealTime(object1.optString("realTime"));
                bean.setYear(object1.optString("year"));

                JSONArray array3 = object1.getJSONArray("titleOthersCn");
                String[] titleOthersCn = new String[array3.length()];
                for (int j=0;j<array3.length();j++){
                    titleOthersCn[i] = array3.getString(i);
                }
                bean.setTitleOthersCn(titleOthersCn);
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
