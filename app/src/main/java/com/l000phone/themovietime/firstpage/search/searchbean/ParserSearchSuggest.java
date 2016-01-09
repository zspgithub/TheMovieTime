package com.l000phone.themovietime.firstpage.search.searchbean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class ParserSearchSuggest {

    public static List<SearchSuggestBean> getSearchSuggest(String json){
        List<SearchSuggestBean> list = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("suggestions");
            for (int i=0;i<array.length();i++){
                JSONObject object1 = array.getJSONObject(i);
                SearchSuggestBean bean = new SearchSuggestBean();
                bean.setContentType(object1.optString("contentType"));
                bean.setCover(object1.optString("cover"));
                bean.setDirector(object1.optString("director"));
                bean.setId(object1.optString("id"));
                bean.setLocationName(object1.optString("locationName"));
                bean.setMovieType(object1.optString("movieType"));
                bean.setTitlecn(object1.optString("titlecn"));
                bean.setTitleen(object1.optString("titleen"));
                bean.setType(object1.optString("type"));
                bean.setYear(object1.optString("year"));
                bean.setrLocation(object1.optString(""));
                list.add(bean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

}
