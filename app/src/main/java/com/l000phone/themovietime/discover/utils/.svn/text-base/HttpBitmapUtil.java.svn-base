package com.l000phone.themovietime.discover.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 15-11-14.
 */
public class HttpBitmapUtil {

    public static Bitmap getBitmap(String url){
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            //定义一个网络对象
            URLConnection connection = new URL(url).openConnection();
            HttpURLConnection con = (HttpURLConnection)connection;
            in = con.getInputStream();//获取字节流
            BufferedInputStream bis = new BufferedInputStream(in);
            bitmap = BitmapFactory.decodeStream(bis);//通过decode方法把流转化成图片
            in.close();
            bis.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
