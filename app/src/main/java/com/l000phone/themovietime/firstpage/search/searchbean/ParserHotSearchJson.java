package com.l000phone.themovietime.firstpage.search.searchbean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 热门搜索解析类
 *
 * 
 */
public class ParserHotSearchJson {

    public static String[] getHotSearch(String json){

        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("keywords");
            String[] keywords = new String[array.length()];
            for (int i=0;i<array.length();i++){
                keywords[i] = array.getString(i);
            }
            return keywords;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }

}
