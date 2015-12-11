package com.l000phone.themovietime.utils;

/**
 * 联网请求回调接口
 *
 * Created by Administrator on 15-11-13.
 */
public interface HttpCallback {

    //请求成功
    public void successGetResult(String result);

    //请求失败
    public void filtureGetResult(String result);

}
