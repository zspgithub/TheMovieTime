package com.l000phone.themovietime.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import com.l000phone.themovietime.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片下载工具类
 * <p/>
 * 下载后将图片存入存储卡
 * <p/>
 *  
 */
public class ImageUtils {

    private ExecutorService mThreadPool;
    private Handler mHandler = new Handler();
    private Bitmap bitmap;

    private final static String IMAGE_PATH = Environment.getExternalStorageDirectory() + "/themovietime/pic/";

    public ImageUtils() {

        File file = new File(IMAGE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        mThreadPool = Executors.newFixedThreadPool(5);

    }

    //给Image设置图片
    public void loadImage(final String path, final ImageView iView) {

        //下载地址为空则返回空值
        if (path == null) {
            return;
        }

        final InputStream is = readImageCache(path);

        if (is != null) {  //如果sdcard中存在该文件则从sdcard中读取
            setImage(path, is, iView);
        } else {  //如果sdcard中不存在该文件就下载
            downloadImage(path, iView);
        }
    }

    //从网络下载图片
    private void downloadImage(final String path, final ImageView iView) {

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                InputStream is = null;
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);

                    is = connection.getInputStream();
                    if (connection.getResponseCode() == 200) {

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int len = 0;
                        byte[] buf = new byte[1024];
                        while((len = is.read(buf))!=-1){
                            baos.write(buf,0,len);
                        }
                        baos.flush();
                        writeImageCache(baos, path);//将图片下载并写入sdcard
                        baos.close();
                    }
                    setImage(path, is, iView);
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // 将图片下载到存储卡
    private void writeImageCache(ByteArrayOutputStream baos, String path) {

        try {
            FileOutputStream fos = new FileOutputStream(new File(
                    IMAGE_PATH,path.substring(path.lastIndexOf("/") + 1)));
            fos.write(baos.toByteArray());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从sdcard总读数据
    private InputStream readImageCache(String path) {

        //下载路径为空，则返回空
        if (path == null) {
            return null;
        }

        //sdcard中不存在该文件，则返回空
        File file = new File(IMAGE_PATH, path.substring(path.lastIndexOf("/") + 1));
        if (!file.exists()) {
            return null;
        }

        //否则就返回该文件的输入流
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return is;
    }

    //给ImageView设置图片
    private void setImage(final String path, final InputStream iStream, final ImageView iView) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                if (iStream!=null) {

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    options.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeStream(iStream,null,options);

                    iView.setImageBitmap(bitmap);
                } else {
                    iView.setImageResource(R.mipmap.mine_main_title_bg);
                }
            }
        });
    }

    /**
     * 往集合中填充数据
     *
     * @param path
     */
    public void loadImg(final String path, final List<Bitmap> list, final int index){

        if (path == null){
            return;
        }

        String fileName = path.substring(path.lastIndexOf("/")+1);
        File file = new File(IMAGE_PATH,fileName);

        LogUtil.log("first",file.getAbsolutePath()+"图片路径");

        if (file.exists()){
            list.add(index,BitmapFactory.decodeFile(file.getAbsolutePath()));
            return;
        }

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(path);
                    LogUtil.log("first","11111111111111");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    InputStream is = connection.getInputStream();
                    if (connection.getResponseCode() == 200) {
                        LogUtil.log("first","网络连接成功");
                        bitmap = BitmapFactory.decodeStream(is);
                        list.add(index,bitmap);
                        LogUtil.log("first","网络下标"+index);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int len = 0;
                        byte[] buf = new byte[1024];
                        while((len = is.read(buf))!=-1){
                            baos.write(buf,0,len);
                        }
                        baos.flush();
                        writeImageCache(baos, path);//将图片下载并写入sdcard
                        baos.close();
                    }
                    is.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        if (bitmap == null){
            BitmapFactory.decodeResource(Resources.getSystem(),R.mipmap.ic_launcher);
        }
        LogUtil.log("first","图片连接222222"+path);

}

}
