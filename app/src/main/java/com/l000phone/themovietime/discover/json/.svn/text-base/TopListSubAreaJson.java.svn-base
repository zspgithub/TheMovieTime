package com.l000phone.themovietime.discover.json;

import com.l000phone.themovietime.discover.bean.TopListSubArea;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-11-18.
 */
public class TopListSubAreaJson {
    public static TopListSubArea getJson(String jsonString){
        TopListSubArea subArea=new TopListSubArea();
        try {
            JSONObject object=new JSONObject(jsonString);
            JSONObject object1=object.getJSONObject("topList");
            if (object1!=null){
                subArea.setTitle(object1.getString("title"));
                subArea.setId(object1.getString("id"));
                subArea.setImageUrl(object1.getString("imageUrl"));
                subArea.setType(object1.getString("type"));
            }
            return subArea;

        } catch (JSONException e) {

        }
        return null;
    }


}
