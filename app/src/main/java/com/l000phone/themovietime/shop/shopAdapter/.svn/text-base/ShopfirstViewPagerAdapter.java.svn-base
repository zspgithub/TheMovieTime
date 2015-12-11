package com.l000phone.themovietime.shop.shopAdapter;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.l000phone.themovietime.shop.shopbean.Scroll;

import java.util.List;

/**
 * Created by bfc on 2015/11/14.
 */
public class ShopfirstViewPagerAdapter extends PagerAdapter {

    private List<ImageView> list;
    private List<Scroll> scrolls;
    private Handler handler;

    public ShopfirstViewPagerAdapter(List<ImageView> list,List<Scroll> scrolls,Handler handler) {
        this.list = list;
        this.handler = handler;
        this.scrolls = scrolls;
    }


    @Override
    public int getCount() {
        int ret = 0;
        if (list!=null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(list.get(position));
        list.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = scrolls.get(position).getUrl();

                Message message = new Message();

                message.what = 94;

                message.obj = url;

                handler.sendMessage(message);
            }
        });
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
