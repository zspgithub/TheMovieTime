package com.l000phone.themovietime.fragments;


//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.user.LoginActivity;
import com.l000phone.themovietime.user.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener {


    private View view;
    private Button user_register;
    private Button user_login;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user, container, false);

        initView();

        return view;
    }

    //初始化布局
    private void initView() {

        user_register = (Button) view.findViewById(R.id.user_register);
        user_login = (Button) view.findViewById(R.id.user_login);
        user_register.setOnClickListener(this);
        user_login.setOnClickListener(this);


    }

    //各个按钮的点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            //登录按钮
            case R.id.user_login:
                useLogin();
                break;
            //注册按钮
            case R.id.user_register:
                userRegister();
                break;

        }
    }

    //注册方法
    private void userRegister() {

        Intent intent = new Intent(getActivity(),RegisterActivity.class);
        startActivity(intent);

    }

    //登录方法
    private void useLogin() {

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

    }


}
