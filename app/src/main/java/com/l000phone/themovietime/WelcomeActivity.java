package com.l000phone.themovietime;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.l000phone.themovietime.firstpage.asynctasks.CityAsyncTask;
import com.l000phone.themovietime.firstpage.bean.CityBean;
import com.l000phone.themovietime.utils.CitySelect;
import com.l000phone.themovietime.utils.LogUtil;
import com.l000phone.themovietime.firstpage.asynctasks.CityAsyncTask.CityCallback;

import java.util.List;

public class WelcomeActivity extends BaseActivity implements CityCallback{
    private CitySelect citySelect = new CitySelect();
    private TextView tv;
    private LocationClient mLocationClient;
    private String cityName = "北京";
    private String PATH = "http://api.m.mtime.cn/Showtime/HotCitiesByCinema.api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ConnectivityManager netManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = netManager.getActiveNetworkInfo();
        if (netInfo== null || !netInfo.isConnected()) {
            Intent intent = new Intent(WelcomeActivity.this, NetFailureActivity.class);
            startActivity(intent);
            WelcomeActivity.this.finish();
        }

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info== null || !info.isConnected()) {
            Intent intent = new Intent(WelcomeActivity.this, NetFailureActivity.class);
            startActivity(intent);
            WelcomeActivity.this.finish();
        }

        LogUtil.log("wel", "activity执行");
        mLocationClient = new LocationClient(getApplicationContext());
        setLocationOption();
        mLocationClient.registerLocationListener(new MyLocationListener());

        new CityAsyncTask(this).execute(PATH);

    }

    //设置参数
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setScanSpan(500);//仅定位一次
        option.setCoorType("db0911");
        option.setOpenGps(true);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);

        mLocationClient.setLocOption(option);
        LogUtil.log("wel", "设置参数");

    }

    //异步解析到城市Json后，传给CitySelect
    @Override
    public void getCities(List<CityBean> list) {

        citySelect.setList(list);
        mLocationClient.start();

    }

    //位置监听事件
    class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            LogUtil.log("wel", "位置监听执行");
            LogUtil.log("wel","纬度："+bdLocation.getLatitude()+",经度："+bdLocation.getLongitude());
           // LogUtil.log("wel",bdLocation.getCity());
            String city = bdLocation.getCity();
            if (city!=null){
            cityName = city.substring(0,city.length()-1);
            LogUtil.log("wel", cityName);
            citySelect.setCityName(cityName);}

            Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            WelcomeActivity.this.finish();
        }
    }
}
