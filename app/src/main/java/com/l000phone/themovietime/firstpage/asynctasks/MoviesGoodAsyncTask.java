package com.l000phone.themovietime.firstpage.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import com.l000phone.themovietime.firstpage.bean.MoviesGoodBean;
import com.l000phone.themovietime.firstpage.utils.ParserMovieGoodBean;
import com.l000phone.themovietime.utils.HttpUtil;

/**
 * 最下边每日佳片
 *
 * 
 */
public class MoviesGoodAsyncTask extends AsyncTask<String, Void, MoviesGoodBean> {

    private Context context;
    private MoviesGoodCallback callback;

    public MoviesGoodAsyncTask(Context context, MoviesGoodCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected MoviesGoodBean doInBackground(String... params) {

        String json = new String(HttpUtil.postDownload(params[0],params[1]));
        MoviesGoodBean bean = ParserMovieGoodBean.getMovieGood(json);
        return bean;
    }

    @Override
    protected void onPostExecute(MoviesGoodBean bean) {
        super.onPostExecute(bean);
        callback.getMoviesGood(bean);
    }

    public interface MoviesGoodCallback{

        public void getMoviesGood(MoviesGoodBean bean);

    }

}
