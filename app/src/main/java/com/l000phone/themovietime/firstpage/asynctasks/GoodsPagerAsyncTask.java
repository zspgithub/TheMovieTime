package com.l000phone.themovietime.firstpage.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import com.l000phone.themovietime.firstpage.bean.GoodsPagerBean;
import com.l000phone.themovietime.firstpage.utils.ParserGoodsBean;
import com.l000phone.themovietime.utils.HttpUtil;
import com.l000phone.themovietime.utils.LogUtil;

import java.util.List;

/**
 * 中间自动滑动广告条的异步任务类
 *
 * Created by Administrator on 15-11-16.
 */
public class GoodsPagerAsyncTask extends AsyncTask<String,Void,List<GoodsPagerBean>> {

    private Context context;
    private GoodsPagerCallback goodsPagerCallback;

    public GoodsPagerAsyncTask(Context context, GoodsPagerCallback goodsPagerCallback) {
        this.context = context;
        this.goodsPagerCallback = goodsPagerCallback;
    }

    @Override
    protected List<GoodsPagerBean> doInBackground(String... params) {

        String json = new String(HttpUtil.postDownload(params[0],params[1]));
        List<GoodsPagerBean> list = ParserGoodsBean.getGoodsPagerBeans(json);
        LogUtil.log("first","定时滑动下载成功,长度:"+list.size());
        return list;
    }

    @Override
    protected void onPostExecute(List<GoodsPagerBean> goodsPagerBeans) {
        super.onPostExecute(goodsPagerBeans);
        if (goodsPagerBeans!=null){
            goodsPagerCallback.getGoodsPager(goodsPagerBeans);
        }


    }

    //List<GoodsPagerBean>回调接口
    public interface GoodsPagerCallback{

        public void getGoodsPager(List<GoodsPagerBean> list);

    }

}
