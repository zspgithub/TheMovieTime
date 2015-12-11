package com.l000phone.themovietime.fragments;


//import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.adapters.FragmentAdapter;
import com.l000phone.themovietime.discover.fragments.NewsFragment;
import com.l000phone.themovietime.discover.fragments.PrevueFragment;
import com.l000phone.themovietime.discover.fragments.ReviewFragment;
import com.l000phone.themovietime.discover.fragments.TopListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_discover, container, false);
        view = inflater.inflate(R.layout.fragment_discover,null);
        initView();

        return view;
    }



    //初始化tablayout以及进来的第一个页面
    private void initView() {

        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.discover_tablayout);
        //创建标签
        TabLayout.Tab tabNews=tabLayout.newTab();
        tabNews.setText("新闻");
        tabLayout.addTab(tabNews);

        TabLayout.Tab tabPrevue=tabLayout.newTab();
        tabPrevue.setText("预告片");
        tabLayout.addTab(tabPrevue);

        TabLayout.Tab tabTopList=tabLayout.newTab();
        tabTopList.setText("排行榜");
        tabLayout.addTab(tabTopList);

        TabLayout.Tab tabReview=tabLayout.newTab();
        tabReview.setText("影评");
        tabLayout.addTab(tabReview);

        //设置tablayout为可滑动
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        final ViewPager viewPager= (ViewPager) view.findViewById(R.id.discover_viewPager);
        List<Fragment>list=new ArrayList<>();

        //给List填充数据，然后用适配器添加到ViewPager中
        NewsFragment newsFragment=new NewsFragment();
        list.add(newsFragment);

        PrevueFragment prevueFragment=new PrevueFragment();
        list.add(prevueFragment);

        TopListFragment topListFragment=new TopListFragment();
        list.add(topListFragment);

        ReviewFragment reviewFragment=new ReviewFragment();
        list.add(reviewFragment);

        FragmentAdapter adapter=new FragmentAdapter(getActivity().getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);

        //Viewpager和Tablayout联动

        //1:ViewPager滑动时，TabLayout动起来
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Tablayout选中时viewPager联动
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}












