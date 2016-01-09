package com.l000phone.themovietime.user.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登录解析类
 *
 *  
 */
public class ParserLogin {

    public static LoginBean getLoginBean(String json){
        LoginBean bean = new LoginBean();
        try {
            JSONObject object = new JSONObject(json);
            bean.setCodeId(object.optString("codeId"));
            bean.setCodeUrl(object.optString(""));
            bean.setError(object.optString("error"));
            bean.setHeadPic(object.optString("headPic"));
            bean.setIsVirtualUser(object.optBoolean("isVirtualUser"));
            bean.setMobile(object.optString("mobile"));
            bean.setNickname(object.optString("nickname"));
            bean.setServiceEmail(object.optString("serviceEmail"));
            bean.setStatus(object.optInt("status"));
            bean.setSuccess(object.optBoolean("success"));
            bean.setTwitterId(object.optString("twitterId"));
            bean.setUserId(object.optInt("userId"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }

}
