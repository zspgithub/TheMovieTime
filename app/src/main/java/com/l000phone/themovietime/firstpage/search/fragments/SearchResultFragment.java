package com.l000phone.themovietime.firstpage.search.fragments;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.search.adapter.SearchResultPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    private String keyword;//关键字
    private View view;
    private TabLayout first_search_result_tablayout;
    private ViewPager first_search_result_viewpager;
    private TabLayout.Tab movieTab;
    private TabLayout.Tab actorTab;
    private TabLayout.Tab theatreTab;
    private List<Fragment> fragments;
    private SearchResultPagerAdapter adapter;


    public SearchResultFragment() {
        // Required empty public constructor
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.first_search_result, container, false);

        Bundle bundle = getArguments();
        keyword = bundle.getString("keyword", null);

        initView();
        initViewPager();

        return view;
    }

    private void initView() {

        first_search_result_tablayout = (TabLayout) view.findViewById(R.id.first_search_result_tablayout);
        first_search_result_viewpager = (ViewPager) view.findViewById(R.id.first_search_result_viewpager);
        first_search_result_tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        first_search_result_tablayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        movieTab = first_search_result_tablayout.newTab();
        movieTab.setText("影片");
        first_search_result_tablayout.addTab(movieTab);

        actorTab = first_search_result_tablayout.newTab();
        actorTab.setText("影人");
        first_search_result_tablayout.addTab(actorTab);

        theatreTab = first_search_result_tablayout.newTab();
        theatreTab.setText("影院");
        first_search_result_tablayout.addTab(theatreTab);


    }

    private void initViewPager() {

        fragments = new ArrayList<>();

        for (int i=0;i<3;i++){

            SearchResultListFragment fragment = new SearchResultListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index",i);
            bundle.putString("keyword",keyword);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        adapter = new SearchResultPagerAdapter(getFragmentManager(),fragments);
        first_search_result_viewpager.setAdapter(adapter);

        first_search_result_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(first_search_result_tablayout));

        first_search_result_tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                first_search_result_viewpager.setCurrentItem(tab.getPosition());
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
