package com.l000phone.themovietime.firstpage.search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.l000phone.themovietime.firstpage.search.searchbean.SearchResultBean;

import java.util.List;

/**
 * 搜索结果Fragment适配器
 *
 * Created by Administrator on 15-11-19.
 */
public class SearchResultPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public SearchResultPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
