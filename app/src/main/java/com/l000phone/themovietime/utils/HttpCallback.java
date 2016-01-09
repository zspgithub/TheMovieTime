package com.l000phone.themovietime.utils;

/**
 * 联网请求回调接口
 *
 *  
 */
public interface HttpCallback {

    //请求成功
    public void successGetResult(String result);

    //请求失败
    public void filtureGetResult(String result);

}
