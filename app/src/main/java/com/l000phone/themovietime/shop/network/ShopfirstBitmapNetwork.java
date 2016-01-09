package com.l000phone.themovietime.shop.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;


/**解析图片的网络请求类
 * 
 */
public class ShopfirstBitmapNetwork {


    public static void bitMapUtils(Context context,ImageView imageView,String url,Handler handler){


        BitmapUtils bitmapUtils = new BitmapUtils(context);

        bitmapUtils.display(imageView,url);

        Message message = new Message();

        message.what = 11;

        handler.sendMessage(message);

    }

    public static void navigBitMapUtils(Context context,ImageView imageView,String url,Handler handler){


        BitmapUtils bitmapUtils = new BitmapUtils(context);

        bitmapUtils.display(imageView,url);

        Message message = new Message();

        message.what = 12;

        handler.sendMessage(message);

    }


    public static void navigatorFirthBitMapUtils(Context context,ImageView imageView,String url,Handler handler){


        BitmapUtils bitmapUtils = new BitmapUtils(context);

        bitmapUtils.display(imageView,url);

        Message message = new Message();


        message.what = 13;

        handler.sendMessage(message);

    }

    public static void CellABitMapUtils(Context context,ImageView imageView,String url){


        BitmapUtils bitmapUtils = new BitmapUtils(context);

        bitmapUtils.display(imageView, url);

    }

    public static void CellCBitMapUtils(Context context,ImageView imageView1,String url1,ImageView imageView2,String url2){


        BitmapUtils bitmapUtils = new BitmapUtils(context);

        bitmapUtils.display(imageView1, url1);

        bitmapUtils.display(imageView2, url2);

    }

    public static void listviewbitMapUtils(Context context,ImageView imageView,String url,Handler handler){


        BitmapUtils bitmapUtils = new BitmapUtils(context);

        bitmapUtils.display(imageView,url);
        Message message = new Message();

        message.what = 21;

        handler.sendMessage(message);

    }
}
