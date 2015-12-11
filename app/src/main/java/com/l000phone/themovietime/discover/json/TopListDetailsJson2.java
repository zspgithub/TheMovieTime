package com.l000phone.themovietime.discover.json;

import com.l000phone.themovietime.discover.bean.TopListDetailsBean2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-11-19.
 */
public class TopListDetailsJson2 {
    public static List<TopListDetailsBean2>getJson(String jsonString){
        List<TopListDetailsBean2>list=new ArrayList<TopListDetailsBean2>();
        try {
            JSONObject object=new JSONObject(jsonString);
            JSONArray array=object.getJSONArray("persons");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1=array.getJSONObject(i);
                if (object1!=null){
                    TopListDetailsBean2 bean2=new TopListDetailsBean2();
                    bean2.setBirthDay(object1.getString("birthDay"));
                    bean2.setBirthLocation(object1.getString("birthLocation"));
                    bean2.setId(object1.getString("id"));
                    bean2.setNameCn(object1.getString("nameCn"));
                    bean2.setNameEn(object1.getString("nameEn"));
                    bean2.setPosterUrl(object1.getString("posterUrl"));
                    bean2.setRankNum(object1.getString("rankNum"));
                    bean2.setRating(object1.getString("rating"));
                    bean2.setSex(object1.getString("sex"));
                    bean2.setSummary(object1.getString("summary"));
                    list.add(bean2);
                }
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


}
