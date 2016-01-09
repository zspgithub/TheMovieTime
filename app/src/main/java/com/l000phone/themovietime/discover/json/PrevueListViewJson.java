package com.l000phone.themovietime.discover.json;

import com.l000phone.themovietime.discover.bean.PrevueListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class PrevueListViewJson {
    public static List<PrevueListView> getJson(String jsonString) {
        List<PrevueListView> prevueListViews = new ArrayList<PrevueListView>();
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = object.getJSONArray("trailers");
            if (array != null && array.length() != 0) {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object1 = array.getJSONObject(i);
                    if (object1 != null) {
                        PrevueListView listView = new PrevueListView();
                        listView.setCoverImg(object1.getString("coverImg"));
                        listView.setHeightUrl(object1.getString("hightUrl"));
                        listView.setId(object1.getString("id"));
                        listView.setMovieId(object1.getString("movieId"));
                        listView.setMovieName(object1.getString("movieName"));
                        listView.setRating(object1.getString("rating"));
                        listView.setSummary(object1.getString("summary"));
                        JSONArray array1 = object1.getJSONArray("type");
                        String[] type = new String[array1.length()];
                        for (int n = 0; n < array1.length(); n++) {
                            type[n] = array1.get(n).toString();
                        }
                        listView.setType(type);
                        listView.setUrl(object1.getString("url"));
                        listView.setVideoLenght(object1.getString("videoLength"));
                        listView.setVideoTitle(object1.getString("videoTitle"));
                        prevueListViews.add(listView);
                    }

                }
            }
            return prevueListViews;


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


}
