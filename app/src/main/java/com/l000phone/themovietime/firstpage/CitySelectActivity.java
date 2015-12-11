package com.l000phone.themovietime.firstpage;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import com.l000phone.themovietime.BaseActivity;
import com.l000phone.themovietime.MainActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.bean.CityBean;
import com.l000phone.themovietime.utils.CitySelect;
import com.l000phone.themovietime.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class CitySelectActivity extends BaseActivity {

    List<CityBean> cityBeans = CitySelect.getList();
    private List<String> cityNames = new ArrayList<>();
    private ListView listView;
    private EditText city_search;
    private TextView city_search_cancel;
    private ArrayAdapter arrayAdapter;
    private ImageView city_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);

        initView();


        for (int i=0;i< cityBeans.size();i++){
            cityNames.add(cityBeans.get(i).getN());
        }

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,cityNames);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityName = cityNames.get(position);
                CitySelect.setCityName(cityName);
                LogUtil.log("city", cityName + ",,已选择");
                Intent intent = new Intent(CitySelectActivity.this, MainActivity.class);
                startActivity(intent);
                CitySelectActivity.this.finish();
            }
        });

        city_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CitySelectActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {

        city_back = (ImageView) findViewById(R.id.city_back);
        listView = (ListView) findViewById(R.id.city_listView);
        city_search = (EditText) findViewById(R.id.city_search);
        city_search_cancel = (TextView) findViewById(R.id.city_search_cancel);

        city_search.addTextChangedListener(new cityWatcher());
        city_search_cancel.setOnClickListener(new CityCancel());

    }

    //EditText输入监听类
    class cityWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            filterData(s.toString());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        //根据输入内容筛选城市
        private void filterData(String s) {

            List<String> list = new ArrayList<>();

            if (TextUtils.isEmpty(s)){
                list = cityNames;
            }else{
                list.clear();
                for (String name : cityNames){

                    if (name.contains(s)){
                        list.add(name);
                    }
                }
                cityNames.clear();
                cityNames.addAll(list);
            }
            
            arrayAdapter.notifyDataSetChanged();
        }

    }

    //TextView监听类，筛选取消
    class CityCancel implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            cityNames.clear();
            city_search.setText("");

            for (int i=0;i<cityBeans.size();i++){

                cityNames.add(cityBeans.get(i).getN());
            }
            arrayAdapter.notifyDataSetChanged();
        }
    }

}
