package com.l000phone.themovietime.shop.shopAdapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by bfc on 2015/11/14.
 */
public class ShopBuyViewPagerAdapter extends PagerAdapter {

    private List<ImageView> list;

    public ShopBuyViewPagerAdapter(List<ImageView> list) {
        this.list = list;
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
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
