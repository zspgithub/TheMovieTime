package com.l000phone.themovietime.firstpage.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.l000phone.themovietime.firstpage.bean.MoviesHotBean;
import com.l000phone.themovietime.firstpage.utils.ParserMovieBean;
import com.l000phone.themovietime.utils.HttpUtil;
import com.l000phone.themovietime.utils.LogUtil;

import java.util.List;

/**
 * 下边今日热点异步任务类
 *
 * 
 */
public class MoviesHotAsyncTask extends AsyncTask<String,Void,List<MoviesHotBean>> {

    private Context context;
    private MoviesHotCallback callback;

    public MoviesHotAsyncTask(Context context, MoviesHotCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected List<MoviesHotBean> doInBackground(String... params) {
        String json = HttpUtil.postDownload(params[0], params[1]);
        LogUtil.log("first","今日热点：--"+json);
        List<MoviesHotBean> list = ParserMovieBean.getMoviesHotBean(json);
        LogUtil.log("first","--"+list.get(0).getImg());
        LogUtil.log("first","--"+list.get(0).getTitle());
        LogUtil.log("first","今日热点长度：--"+list.size());
        return list;
    }

    @Override
    protected void onPostExecute(List<MoviesHotBean> list) {
        super.onPostExecute(list);
        callback.getMoviesHot(list);
    }

    public interface MoviesHotCallback{

        public void getMoviesHot(List<MoviesHotBean> list);

    }

}
