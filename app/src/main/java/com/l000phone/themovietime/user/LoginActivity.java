package com.l000phone.themovietime.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.user.asynctasks.LoginAsyncTask;
import com.l000phone.themovietime.user.asynctasks.LoginAsyncTask.LoginCallback;
import com.l000phone.themovietime.user.bean.LoginBean;
import com.l000phone.themovietime.utils.LogUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginCallback{

    private String path = "http://api.m.mtime.cn/Mobile/Login.api";
    private ImageView login_back;
    private EditText login_account;
    private EditText login_pwd;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {

        login_back = (ImageView) findViewById(R.id.login_back);
        login_account = (EditText) findViewById(R.id.login_account);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_btn = (Button) findViewById(R.id.login_btn);

        login_back.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.login_back:
                LoginActivity.this.finish();
                break;
            case R.id.login_btn:
                String account = login_account.getText().toString();
                String pwd = login_pwd.getText().toString();

                if (TextUtils.isEmpty(account)){
                    Toast.makeText(this,"请输入登录账号！",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(pwd)){
                    Toast.makeText(this,"请输入密码！",Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = getMD5(pwd);
                LogUtil.log("login",password);
                String params = "password="+password+"&email="+account;
                new LoginAsyncTask(LoginActivity.this,this).execute(path,params);
                break;

        }

    }

    //将密码进行MD5加密
    private String getMD5(String pwd) {
        StringBuffer sb = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte[] buf = md.digest();
            int index;
            for (int i=0;i<buf.length;i++){
                index = buf[i];
                if (index<0){
                    index+=256;
                }
                if (index<16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(index));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //登录回调
    @Override
    public void getLogin(LoginBean bean) {

        boolean success = bean.isSuccess();
        if (success){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,bean.getError(),Toast.LENGTH_SHORT).show();
        }

    }
}
