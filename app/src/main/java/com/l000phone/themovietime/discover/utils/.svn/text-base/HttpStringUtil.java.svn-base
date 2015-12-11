package com.l000phone.themovietime.discover.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 15-11-14.
 */
public class HttpStringUtil {
    public static String getString (String strUrl){
        String result="";

        try {
            URL url=new URL(strUrl);

            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("accept", "*/*");

            conn.connect();
            InputStream stream=conn.getInputStream();
            InputStreamReader inReader=new InputStreamReader(stream);
            BufferedReader buffer=new BufferedReader(inReader);
            String strLine=null;
            while ((strLine=buffer.readLine())!=null){
                result+=strLine;
            }
            inReader.close();;
            conn.disconnect();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
