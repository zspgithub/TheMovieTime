package com.l000phone.themovietime.firstpage.search;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.ArraySet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.MainActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.search.fragments.SearchReadyFragment;
import com.l000phone.themovietime.firstpage.search.fragments.SearchResultFragment;
import com.l000phone.themovietime.firstpage.search.fragments.SearchStartFragment;
import com.l000phone.themovietime.utils.FragmentChangeHelper;
import com.l000phone.themovietime.utils.LogUtil;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class SearchMovieActivity extends BaseActivity implements View.OnClickListener {

    private ImageView first_search_back;
    private EditText first_search_et;
    private TextView first_search_tv;
    private LinearLayout first_search_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_search_movie);

        initView();

    }

    //初始化控件
    private void initView() {

        first_search_back = (ImageView) findViewById(R.id.first_search_back);
        first_search_et = (EditText) findViewById(R.id.first_search_et);
        first_search_tv = (TextView) findViewById(R.id.first_search_tv);
        first_search_container = (LinearLayout) findViewById(R.id.first_search_container);
        first_search_back.setOnClickListener(this);
        first_search_tv.setOnClickListener(this);

        //默认为第一个Fragment
        final FragmentChangeHelper helper = new FragmentChangeHelper();
        helper.setTargetFragment(new SearchReadyFragment());
        changeFragment(helper);

        //给Edittext设置监听
        first_search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                FragmentChangeHelper helper1 = new FragmentChangeHelper();
                helper1.setTargetFragment(new SearchStartFragment());
                Bundle bundle = new Bundle();
                bundle.putString("keyword",s.toString());
                helper1.setBundle(bundle);
                LogUtil.log("search",s.toString());
                changeFragment(helper1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //各个按钮点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //返回到MainActivity
            case R.id.first_search_back:
                Intent intent = new Intent(SearchMovieActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            //点击搜索按钮，切换到搜索结果Fragment
            case R.id.first_search_tv:
                if (TextUtils.isEmpty(first_search_et.getText().toString())){
                    return;
                }
                LogUtil.log("search","点击");
                SharedPreferences sp = getSharedPreferences("search",MODE_PRIVATE);
                HashSet<String> history = (HashSet<String>) sp.getStringSet("history", null);
                if (history==null){
                    history = new HashSet<>();
                }


                LogUtil.log("search", "点击搜索11111");
                FragmentChangeHelper helper = new FragmentChangeHelper();
                helper.setTargetFragment(new SearchResultFragment());
                Bundle bundle = new Bundle();
                bundle.putString("keyword",first_search_et.getText().toString());
                helper.setBundle(bundle);
                changeFragment(helper);
                LogUtil.log("search","点击搜索22222");
                break;


        }
    }

    //切换Fragment的方法
    public void changeFragment(FragmentChangeHelper helper) {
        //获取helper当中信息

        //要跳转的目标fragment
        Fragment targetFragment = helper.getTargetFragment();

        //需要给目标Fragment传bundle
        Bundle bundle = helper.getBundle();
        targetFragment.setArguments(bundle);

        //是否清空回退栈
        boolean clearBackStack = helper.isClearBackStack();

        //是否加入回退栈
        String fragmentTag = helper.getFragmentTag();

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        //是否加入回退栈
        if (fragmentTag != null) {
            transaction.addToBackStack(fragmentTag);
        }

        //是否清空回退栈
        if(clearBackStack){
            manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        //传递Bundle数据
        if (bundle != null) {
            targetFragment.setArguments(bundle);
        }

        transaction.replace(R.id.first_search_container, targetFragment);

        transaction.commit();

    }

}
