package com.l000phone.themovietime.discover.json;

import com.l000phone.themovietime.discover.bean.PrevueSubArea;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-11-18.
 */
public class PrevueSubAreaJson {
    public static PrevueSubArea getJson(String jsonString){
        PrevueSubArea subArea=new PrevueSubArea();

        try {
            JSONObject object=new JSONObject(jsonString);
            JSONObject object1=object.getJSONObject("trailer");
            if (object1!=null){
                subArea.setHightUrl(object1.getString("hightUrl"));
                subArea.setImageUrl(object1.getString("imageUrl"));
                subArea.setMovieId(object1.getString("movieId"));
                subArea.setMp4Url(object1.getString("mp4Url"));
                subArea.setTitle(object1.getString("title"));
                subArea.setTrailerID(object1.getString("trailerID"));
            }
            return subArea;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

}
