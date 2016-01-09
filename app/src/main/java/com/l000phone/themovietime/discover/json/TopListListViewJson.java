package com.l000phone.themovietime.discover.json;

import com.l000phone.themovietime.discover.bean.TopListListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class TopListListViewJson {
    public static List<TopListListView>getJson(String jsonString){
        List<TopListListView>listViews=new ArrayList<TopListListView>();
        try {
            JSONObject object=new JSONObject(jsonString);
            if (object!=null){
                JSONArray array=object.getJSONArray("topLists");
                if (array!=null&&array.length()!=0){
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1=array.getJSONObject(i);
                        if (object1!=null){
                            TopListListView list=new TopListListView();
                            list.setType(object1.getString("type"));
                            list.setId(object1.getString("id"));
                            list.setSummary(object1.getString("summary"));
                            list.setTopListNameCn(object1.getString("topListNameCn"));
                            list.setTopListNameEn(object1.getString("topListNameEn"));
                            listViews.add(list);
                        }
                    }
                }
            }
            return listViews;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return  null;
    }



}
