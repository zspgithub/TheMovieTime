package com.l000phone.themovietime.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 联网请求类
 * <p/>
 *  
 */
public class HttpUtil {

    /**
     * post请求的下载方法
     *
     * @param path
     * @param param
     */
    public static String postDownload(final String path, final String param) {

        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setReadTimeout(6000);
            //向connection写入请求参数
            OutputStream os = connection.getOutputStream();
            os.write(param.getBytes());
            os.flush();
            os.close();

            //读取数据
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream is = connection.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];

                while ((len = is.read(buf)) != -1) {
                    builder.append(new String(buf, 0, len));
                }
                is.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * get请求的下载方法
     *
     * @param path
     */
    public static byte[] getDownload(final String path) {

        if (path == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(6000);

            //读取数据
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream is = connection.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {

                    baos.write(buf, 0, len);

                }
                is.close();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

}
