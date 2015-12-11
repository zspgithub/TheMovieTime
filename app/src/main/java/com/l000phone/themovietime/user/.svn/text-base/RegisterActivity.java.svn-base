package com.l000phone.themovietime.user;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.R;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private String desPassword = "qGZUD6REl0P63pHe3h2DWQ==";
    private String sex = "1";


    private ImageView register_bar_back;
    private EditText register_mobile;
    private CheckBox register_pwd_switch;
    private EditText register_pwd;
    private Button register_code;
    private RadioGroup register_sex_group;
    private CheckBox register_clause_btn;
    private TextView register_clause_tv;
    private Button register_btn;
    private ImageView register_code_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

    }

    private void initView() {

        register_bar_back = (ImageView) findViewById(R.id.register_bar_back);
        register_mobile = (EditText) findViewById(R.id.register_mobile);
        register_pwd_switch = (CheckBox) findViewById(R.id.register_pwd_switch);
        register_pwd = (EditText) findViewById(R.id.register_pwd);
        register_code = (Button) findViewById(R.id.register_code);
        register_sex_group = (RadioGroup) findViewById(R.id.register_sex_group);
        register_clause_btn = (CheckBox) findViewById(R.id.register_clause_btn);
        register_clause_tv = (TextView) findViewById(R.id.register_clause_tv);
        register_btn = (Button) findViewById(R.id.register_btn);
        register_code_img = (ImageView) findViewById(R.id.register_code_img);

        register_code.setOnClickListener(this);

//        if (register_pwd_switch.isChecked()){
//            register_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//
//        }else{
//            register_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//
//        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.register_code:
                register_code_img.setVisibility(View.VISIBLE);

                break;
            case R.id.register_clause_tv:

                break;
            case R.id.register_btn:

                RegisterActivity.this.finish();
                break;

        }

    }
}
