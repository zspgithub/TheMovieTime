package com.l000phone.themovietime.firstpage.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.l000phone.themovietime.firstpage.bean.FirstMovieBean;
import com.l000phone.themovietime.firstpage.bean.FirstTopBean;
import com.l000phone.themovietime.firstpage.utils.ParserTopJson;
import com.l000phone.themovietime.utils.HttpUtil;
import com.l000phone.themovietime.utils.LogUtil;

import java.util.List;

/**
 * 异步任务类，得到json字符串，并解析
 *
 * 
 */
public class FirstTopMovieAsyncTask extends AsyncTask<String,Void,List<FirstMovieBean>> {

    private Context context;
    private FirstTopMovieCallback mFirstTopMovieCallback;
    private FirstTopCallback mFirstTopCallback;
    private FirstTopBean firstTopBean;

    public FirstTopMovieAsyncTask(FirstTopMovieCallback mFirstTopMovieCallback,FirstTopCallback firstTopCallback, Context context) {
        this.mFirstTopMovieCallback = mFirstTopMovieCallback;
        this.mFirstTopCallback = firstTopCallback;
        this.context = context;
    }

    @Override
    protected List<FirstMovieBean> doInBackground(String... params) {

        String json = new String(HttpUtil.getDownload(params[0]));
        LogUtil.log("first","下载的Json字符串："+json);
        firstTopBean = ParserTopJson.getFirstTopBean(json);

        List<FirstMovieBean> list = ParserTopJson.getMovies(json);
        return list;
    }

    @Override
    protected void onPostExecute(List<FirstMovieBean> firstMovieBeans) {
        super.onPostExecute(firstMovieBeans);
        if (firstMovieBeans!=null){
            mFirstTopMovieCallback.getFirstTopMovie(firstMovieBeans);
            mFirstTopCallback.getFirstTop(firstTopBean);
        }

    }

    public interface FirstTopMovieCallback{
        public void getFirstTopMovie(List<FirstMovieBean> list);
    }

    public interface FirstTopCallback{
        public void getFirstTop(FirstTopBean firstTopBean);
    }

}
