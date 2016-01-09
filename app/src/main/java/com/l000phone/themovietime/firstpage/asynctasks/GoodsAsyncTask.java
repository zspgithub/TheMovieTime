package com.l000phone.themovietime.firstpage.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import com.l000phone.themovietime.firstpage.bean.GoodsGotoPageBean;
import com.l000phone.themovietime.firstpage.bean.GoodsSubBean;
import com.l000phone.themovietime.firstpage.utils.ParserGoodsBean;
import com.l000phone.themovietime.utils.HttpUtil;

import java.util.List;

/**
 *
 */
public class GoodsAsyncTask extends AsyncTask<String,Void,List<GoodsSubBean>> {

    private Context context;
    private GoodsGotoPageCallback gotoPageCallback;
    private GoodsSubCallback subCallback;
    private List<GoodsGotoPageBean> list1;

    public GoodsAsyncTask(Context context, GoodsSubCallback subCallback, GoodsGotoPageCallback gotoPageCallback) {
        this.context = context;
        this.gotoPageCallback = gotoPageCallback;
        this.subCallback = subCallback;
    }

    @Override
    protected List<GoodsSubBean> doInBackground(String... params) {

        String json = new String(HttpUtil.postDownload(params[0],params[1]));
        //商品
        List<GoodsSubBean> list = ParserGoodsBean.getGoodsSubBean(json);
        //商品详情
        list1 = ParserGoodsBean.getGoodsPageBean(json);

        return list;
    }

    @Override
    protected void onPostExecute(List<GoodsSubBean> goodsSubBeans) {
        super.onPostExecute(goodsSubBeans);

        subCallback.getGoodsSub(goodsSubBeans);
        gotoPageCallback.getGoodsGotoPage(list1);
    }

    public interface GoodsSubCallback{
        public void getGoodsSub(List<GoodsSubBean> list);
    }

    public interface GoodsGotoPageCallback{
        public void getGoodsGotoPage(List<GoodsGotoPageBean> list);
    }

}
