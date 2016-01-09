package com.l000phone.themovietime.firstpage.asynctasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;

import com.l000phone.themovietime.firstpage.bean.FirstMovieBean;
import com.l000phone.themovietime.firstpage.utils.ParserTopJson;
import com.l000phone.themovietime.utils.HttpUtil;
import com.l000phone.themovietime.utils.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页最上边可滑动图片
 *
 * 
 */
public class FirstTopImgAsyncTask extends AsyncTask<String,Void,Bitmap> {

    private Context context;
    private FirstImgCallback firstCallback;
    private final static String IMAGE_PATH = Environment.getExternalStorageDirectory() + "/themovietime/pic/";
    public FirstTopImgAsyncTask(Context context, FirstImgCallback firstCallback) {
        this.context = context;
        this.firstCallback = firstCallback;
        File file = new File(IMAGE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 4;
        String fileName = params[0].substring(params[0].lastIndexOf("/")+1);
        File file = new File(IMAGE_PATH,fileName);

        if (file.exists()){
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
        }else{
            byte[] buf = HttpUtil.getDownload(params[0]);
            bitmap = BitmapFactory.decodeByteArray(buf,0,buf.length,options);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(buf);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        firstCallback.getImg(bitmap);

    }

    public interface FirstImgCallback{

        public void getImg(Bitmap bitmap);

    }

}
