package com.l000phone.themovietime.firstpage.asynctasks;

import android.os.AsyncTask;
import com.l000phone.themovietime.firstpage.bean.CityBean;
import com.l000phone.themovietime.firstpage.utils.ParserCityBean;
import com.l000phone.themovietime.utils.HttpUtil;
import java.util.List;

/**
 * 城市异步任务类
 *
 * Created by Administrator on 15-11-17.
 */
public class CityAsyncTask extends AsyncTask<String,Void,List<CityBean>> {

    private CityCallback callback;

    public CityAsyncTask(CityCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<CityBean> doInBackground(String... params) {
        String json = new String(HttpUtil.getDownload(params[0]));
        List<CityBean> list = ParserCityBean.getCities(json);
        return list;
    }

    @Override
    protected void onPostExecute(List<CityBean> cityBeans) {
        super.onPostExecute(cityBeans);
        callback.getCities(cityBeans);
    }

    public interface CityCallback {
        public void getCities(List<CityBean> list);
    }

}