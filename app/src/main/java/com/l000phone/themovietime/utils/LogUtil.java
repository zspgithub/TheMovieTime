package com.l000phone.themovietime.utils;

import android.util.Log;

/**
 * 打印工具类
 *
 * 项目完成后把flag置为false即可
 *
 *  
 */
public class LogUtil {

    public static final boolean flag = true;

    public static void log(String s1,String s2){

        if (flag){

        Log.i(s1,s2);

        }

    }

}
