package com.l000phone.themovietime.discover.json;

import android.util.Log;

import com.l000phone.themovietime.discover.bean.NewsListView;
import com.l000phone.themovietime.discover.bean.NewsListViewImages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-11-16.
 */
public class NewsListViewJson {

    public static List<NewsListView> getJson(String jsonString) {
        // Log.d("test","-------------------"+jsonString);
        List<NewsListView> listViews = new ArrayList<NewsListView>();
        List<NewsListViewImages> images ;
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = object.getJSONArray("newsList");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                if (object1 != null) {
                    NewsListView listView = new NewsListView();
                    listView.setTitle(object1.optString("title"));
                    //Log.d("test","++++++++++++++"+listView.getTitle());
                    listView.setCommentCount(object1.optString("commentCount"));
                    listView.setId(object1.optString("id"));
                    listView.setImage(object1.optString("image"));
                    listView.setPublishTime(object1.optString("publishTime"));
                   // Log.d("test",listView.getPublishTime());
                    listView.setTag(object1.optString("tag"));
                    listView.setTitle2(object1.optString("title2"));
                    listView.setType(object1.optString("type"));
                    if (object1.optString("type").equals("1")) {
//                        Log.d("test","--------------"+listView.getTitle());
//                        Log.d("test","+++++++++++++++++++"+listView.getPublishTime());

                        JSONArray array1 = object1.getJSONArray("images");
                        if (array1 != null && array1.length() != 0) {
                            images= new ArrayList<NewsListViewImages>();
                            for (int j = 0; j < array1.length(); j++) {
                                JSONObject object2 = array1.getJSONObject(j);
                                if (object2 != null) {
                                    NewsListViewImages images1 = new NewsListViewImages();
                                    images1.setUrl1(object2.optString("url1"));
                                    // Log.d("test","##############"+object2.optString("url1"));
                                    images.add(images1);
                                }

                            }
                            listView.setImages(images);
                        }
                    }
                    listViews.add(listView);
                }

            }
            // Log.d("test","+++++++++++++++++++++++++++"+listViews);
            return listViews;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
