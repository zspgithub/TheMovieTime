package com.l000phone.themovietime.discover.json;

import com.l000phone.themovietime.discover.bean.NewsSubArea;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class NewsSubAreaJson {
    public static NewsSubArea getJson(String jsonString){
        NewsSubArea subArea=new NewsSubArea();
        try {
            JSONObject obj=new JSONObject(jsonString);
            JSONObject object=obj.getJSONObject("news");
            if (object!=null){

                subArea.setTitle(object.getString("title"));
                subArea.setImageUrl(object.getString("imageUrl"));
                subArea.setNewsID(object.getString("newsID"));


            }
            return subArea;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}
