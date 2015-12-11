package com.l000phone.themovietime.firstpage.search.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.l000phone.themovietime.firstpage.search.searchbean.ParserHotSearchJson;
import com.l000phone.themovietime.utils.HttpUtil;

/**
 * 热门搜索的异步任务类
 *
 * Created by Administrator on 15-11-18.
 */
public class SearchHotAsyncTask extends AsyncTask<String,Void,String[]> {

    private Context context;
    private HotSearchCallback callback;

    public SearchHotAsyncTask(Context context, HotSearchCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected String[] doInBackground(String... params) {

        String json = new String(HttpUtil.getDownload(params[0]));
        String[] result = ParserHotSearchJson.getHotSearch(json);
        return result;
    }

    @Override
    protected void onPostExecute(String[] keywords) {
        super.onPostExecute(keywords);

        callback.getHotSearch(keywords);

    }

    public interface HotSearchCallback{

        public void getHotSearch(String[] keywords);

    }

}
