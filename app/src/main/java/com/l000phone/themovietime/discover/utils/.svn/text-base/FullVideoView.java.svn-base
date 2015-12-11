package com.l000phone.themovietime.discover.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Administrator on 15-11-18.
 */
public class FullVideoView extends VideoView {
    //在计算尺寸的方法中，给视频指定宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //mode在布局中声明的宽高，这个宽高，系统中会算出是什么模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int mode1 = MeasureSpec.getMode(heightMeasureSpec);

        if(mode==MeasureSpec.EXACTLY&&mode1==MeasureSpec.EXACTLY){

            //根据mode获取宽度
            int size = MeasureSpec.getSize(widthMeasureSpec);
            //根据mode获取高度
            int size1 = MeasureSpec.getSize(heightMeasureSpec);
            setMeasuredDimension(size,size1);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }


    }

    //两个参数构造方法就可以在布局文件中使用了
    public FullVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
