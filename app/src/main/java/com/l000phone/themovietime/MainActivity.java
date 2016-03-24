package com.l000phone.themovietime;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.l000phone.themovietime.fragments.DiscoverFragment;
import com.l000phone.themovietime.fragments.FirstpageFragment;
import com.l000phone.themovietime.fragments.PayticketFragment;
import com.l000phone.themovietime.fragments.ShopFragment;
import com.l000phone.themovietime.fragments.UserFragment;
import com.l000phone.themovietime.utils.FragmentChangeHelper;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup main_radioGroup;
    private Fragment fragment;


    private FragmentManager manager;
    private FragmentTransaction transaction;
    private FirstpageFragment firstpageFragment;
    private PayticketFragment payticketFragment;
    private ShopFragment shopFragment;
    private DiscoverFragment discoverFragment;
    private UserFragment userFragment;
    private RadioButton main_rbFirstpage;
    private RadioButton main_rbPayticket;
    private RadioButton main_rbShop;
    private RadioButton main_rbDiscover;
    private RadioButton main_rbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		setTitle("sssssss");//zsp modefine
        initView();

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info== null || !info.isConnected()){
            Intent intent = new Intent(MainActivity.this, NetFailureActivity.class);
            startActivity(intent);
            //MainActivity.this.finish();
        }else{

            fragment = new FirstpageFragment();
            FragmentChangeHelper helper = new FragmentChangeHelper();
            helper.setTargetFragment(fragment);
            helper.setIsClearBackStack(true);
            changeFragment(helper);
        }

//        firstpageFragment = new FirstpageFragment();
//        payticketFragment = new PayticketFragment();
 //          shopFragment = new ShopFragment();
//        discoverFragment = new DiscoverFragment();
//        userFragment = new UserFragment();

//        manager = getSupportFragmentManager();
//        transaction = manager.beginTransaction();
//        transaction.replace(R.id.main_container, firstpageFragment);
//        transaction.commit();
//
        main_radioGroup.setOnCheckedChangeListener(this);

    }

    private void initView() {

        main_radioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);

        main_rbFirstpage = (RadioButton) findViewById(R.id.main_rbFirstpage);
        main_rbPayticket = (RadioButton) findViewById(R.id.main_rbPayticket);
        main_rbShop = (RadioButton) findViewById(R.id.main_rbShop);
        main_rbDiscover = (RadioButton) findViewById(R.id.main_rbDiscover);
        main_rbUser = (RadioButton) findViewById(R.id.main_rbUser);

    }

    //RadioGroup监听事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        fragment = null;
       manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        switch (checkedId){

            case R.id.main_rbFirstpage:
//                transaction.replace(R.id.main_container, firstpageFragment);
                fragment = new FirstpageFragment();
                break;

            case R.id.main_rbPayticket:
//                transaction.replace(R.id.main_container, payticketFragment);
                fragment = new PayticketFragment();
                break;

            case R.id.main_rbShop:
               //transaction.replace(R.id.main_container, shopFragment);
                fragment = new ShopFragment();
                break;

            case R.id.main_rbDiscover:
//                transaction.replace(R.id.main_container, discoverFragment);
                fragment = new DiscoverFragment();
                break;

            case R.id.main_rbUser:
//                transaction.replace(R.id.main_container, userFragment);
                fragment = new UserFragment();
                break;
        }
    //   transaction.addToBackStack(null);
   //     transaction.commit();
        FragmentChangeHelper helper = new FragmentChangeHelper();
        helper.setTargetFragment(fragment);

        helper.setIsClearBackStack(true);
        changeFragment(helper);

    }
    public void changeFragment(FragmentChangeHelper helper) {
        //获取helper当中信息

        //要跳转的目标fragment
        Fragment targetFragment = helper.getTargetFragment();

        //需要给目标Fragment传bundle
        Bundle bundle = helper.getBundle();

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

        transaction.replace(R.id.main_container, targetFragment);

        transaction.commit();

        if (targetFragment instanceof FirstpageFragment){
            main_rbFirstpage.setChecked(true);
        }else if (targetFragment instanceof PayticketFragment){
            main_rbPayticket.setChecked(true);
        }else if (targetFragment instanceof ShopFragment){
            main_rbShop.setChecked(true);
        }else if (targetFragment instanceof DiscoverFragment){
            main_rbDiscover.setChecked(true);
        }else if (targetFragment instanceof UserFragment){
            main_rbUser.setChecked(true);
        }

    }

    //按下返回键后弹出提示框询问用户是否确认退出
//写一个退出前弹出询问是否确认退出对话框
    protected void exitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确认退出吗？");
        builder.setPositiveButton("确认", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("取消", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    //当按下返回键时，弹出提示对话框
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0) {
            exitDialog();
        }
        return false;
    }
}
