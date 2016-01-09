package com.l000phone.themovietime.firstpage.search.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.l000phone.themovietime.firstpage.search.searchbean.ParserSearchResult;
import com.l000phone.themovietime.firstpage.search.searchbean.SearchResultBean;
import com.l000phone.themovietime.utils.HttpUtil;

import java.util.List;

/**
 * 搜索结果异步任务类
 *
 * 
 */
public class SearchResultAsyncTask extends AsyncTask<String,Void,List<SearchResultBean>> {

    private Context context;
    private SearchResultCallback callback;

    public SearchResultAsyncTask(Context context, SearchResultCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected List<SearchResultBean> doInBackground(String... params) {

        String json = new String(HttpUtil.postDownload(params[0],params[1]));
        List<SearchResultBean> list = ParserSearchResult.getSearchResults(json);
        return list;
    }

    @Override
    protected void onPostExecute(List<SearchResultBean> searchResultBeans) {
        super.onPostExecute(searchResultBeans);
        callback.getSearchResult(searchResultBeans);
    }

    public interface SearchResultCallback{
        public void getSearchResult(List<SearchResultBean> list);
    }

}
