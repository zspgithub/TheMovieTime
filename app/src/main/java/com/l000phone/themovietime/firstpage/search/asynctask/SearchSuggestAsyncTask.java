package com.l000phone.themovietime.firstpage.search.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.l000phone.themovietime.firstpage.search.searchbean.ParserSearchSuggest;
import com.l000phone.themovietime.firstpage.search.searchbean.SearchSuggestBean;
import com.l000phone.themovietime.utils.HttpUtil;

import java.util.List;

/**
 * 
 */
public class SearchSuggestAsyncTask extends AsyncTask<String,Void,List<SearchSuggestBean>> {

    private Context context;
    private SearchSuggestCallback callback;

    public SearchSuggestAsyncTask(Context context, SearchSuggestCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected List<SearchSuggestBean> doInBackground(String... params) {
        String json = new String(HttpUtil.postDownload(params[0],params[1]));
        List<SearchSuggestBean> list = ParserSearchSuggest.getSearchSuggest(json);
        return list;
    }

    @Override
    protected void onPostExecute(List<SearchSuggestBean> searchSuggestBeans) {
        super.onPostExecute(searchSuggestBeans);
        callback.getSearchSuggest(searchSuggestBeans);
    }

    public interface SearchSuggestCallback{
        public void getSearchSuggest(List<SearchSuggestBean> list);
    }

}
