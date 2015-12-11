package com.l000phone.themovietime.payticket.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.bean.PayticketReyingBean;
import com.l000phone.themovietime.utils.ImageUtils;
import com.l000phone.themovietime.utils.LogUtil;
import com.lidroid.xutils.BitmapUtils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * Created by Administrator on 2015/11/15.
 */
public class PayTicketReyingAdapter extends BaseAdapter{
    private List<PayticketReyingBean> list;
    private LayoutInflater mInflater;
    private Context context;
    private ImageUtils imageUtils = new ImageUtils();
    public PayTicketReyingAdapter(Context context,List<PayticketReyingBean> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int count = 0;
        if (list!=null){
            count = list.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView==null){
            convertView = mInflater.inflate(R.layout.payticket_reying_listitem,null);
            vh = new ViewHolder();
            vh.movieName = ((TextView) convertView.findViewById(R.id.payticket_reying_item_moviename));
            vh.movieScore = ((TextView) convertView.findViewById(R.id.payticket_reying_item_score));
            vh.piaofang = ((TextView) convertView.findViewById(R.id.payticket_reying_item_piaofang));
            vh.time = ((TextView) convertView.findViewById(R.id.payticket_reying_item_time));
            vh.movieJianjie = ((TextView) convertView.findViewById(R.id.payticket_reying_item_jianjie));
            vh.text_3D = ((TextView) convertView.findViewById(R.id.payticket_reying_item_3D));
            vh.jumu = ((TextView) convertView.findViewById(R.id.payticket_reying_item_jumu));
            vh.IMAX = ((TextView) convertView.findViewById(R.id.payticket_reying_item_IMAX));
            vh.movieIcon = ((ImageView) convertView.findViewById(R.id.payticket_reying_item_image));
            vh.goupiao = ((TextView) convertView.findViewById(R.id.payticket_reying_item_goupiao));
            convertView.setTag(vh);
        }else{
            vh = ((ViewHolder) convertView.getTag());
        }
        PayticketReyingBean reyingBean = list.get(position);
        vh.movieName.setText(reyingBean.getT());
        vh.movieScore.setText(reyingBean.getR());
//        vh.time.setText(reyingBean.getRd()+"上映");
        vh.piaofang.setText("今日"+reyingBean.getNearestCinemaCount()
                +"家影院上映"+reyingBean.getNearestShowtimeCount()+"场");

//        BitmapUtils bitmapUtils = new BitmapUtils(context);
//        bitmapUtils.display(vh.movieIcon,reyingBean.getImg());
        LogUtil.log("oooo",reyingBean.getImg());
        imageUtils.loadImage(reyingBean.getImg(),vh.movieIcon);
        if (reyingBean.getIs3D().equals("true")){
            vh.text_3D.setVisibility(View.VISIBLE);
        }else {
            vh.text_3D.setVisibility(View.GONE);
        }
        if (reyingBean.getIsIMAX().equals("true")){
            vh.IMAX.setVisibility(View.VISIBLE);
        }else {
            vh.IMAX.setVisibility(View.GONE);
        }
        if (reyingBean.getIsDMAX().equals("true")){
            vh.jumu.setVisibility(View.VISIBLE);
        }else {
            vh.jumu.setVisibility(View.GONE);
        }
        if (reyingBean.getIsTicket().equals("false")){
            vh.goupiao.setText("查影讯");
            vh.goupiao.setTextColor(Color.GREEN);
            vh.goupiao.setBackgroundResource(R.drawable.payticket_reying_zhiyingxun_bg);
            vh.movieJianjie.setText(reyingBean.getWantedCount()+"人想看"+"-"+reyingBean.getMovieType());
        }else{
            vh.goupiao.setText("购票");
            vh.goupiao.setTextColor(Color.WHITE);
            vh.goupiao.setBackgroundResource(R.drawable.payticket_reying_item_goupiao_bg);
            if (!reyingBean.getCommonSpecial().equals("")){
                vh.movieJianjie.setText("“" + reyingBean.getCommonSpecial());
            }else {
                vh.movieJianjie.setText(reyingBean.getWantedCount()+"人想看"+"-"+reyingBean.getMovieType());
            }
        }
        String str = reyingBean.getRd();
        if (str!=null) {
            if (str.substring(0, 4).equals("2015")) {
                vh.time.setText(str.substring(4, 6) + "月" + str.substring(6) + "日上映");
            } else {
                vh.time.setText(str.substring(0, 4) + "年" + str.substring(4, 6) + "月" + str.substring(6) + "日上映");
            }
        }
        return convertView;
    }
    class ViewHolder{
        ImageView movieIcon;
        TextView movieName;
        TextView movieJianjie;
        TextView movieScore;
        TextView time;
        TextView piaofang;
        TextView text_3D,jumu,IMAX;
        TextView goupiao;
    }
}
