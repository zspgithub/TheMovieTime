package com.l000phone.themovietime.firstpage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.bean.MoviesHotBean;
import com.l000phone.themovietime.utils.ImageUtils;
import com.l000phone.themovietime.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-11-16.
 */
public class MoviesHotAdapter extends BaseAdapter {

    private Context context;
    private List<MoviesHotBean> list;
    private ImageUtils imageUtils;

    public MoviesHotAdapter(Context context, List<MoviesHotBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.first_list_item,null);
            holder = new ViewHolder();
            holder.first_list_item_iv = (ImageView) convertView.findViewById(R.id.first_list_item_iv);
            holder.first_list_item_title = (TextView) convertView.findViewById(R.id.first_list_item_title);
            holder.first_list_item_desc = (TextView) convertView.findViewById(R.id.first_list_item_desc);
            holder.first_list_item_publishTime = (TextView) convertView.findViewById(R.id.first_list_item_publishTime);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        MoviesHotBean moviesHotBean = list.get(position);
        holder.first_list_item_title.setText(moviesHotBean.getTitle());
        holder.first_list_item_desc.setText(moviesHotBean.getDesc());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String format1 = format.format(new Date(System.currentTimeMillis()-Long.valueOf(moviesHotBean.getPublishTime())));
        LogUtil.log("first", format1);
        holder.first_list_item_publishTime.setText(format1);
        LogUtil.log("first", moviesHotBean.getImg());
        imageUtils = new ImageUtils();
        imageUtils.loadImage(moviesHotBean.getImg(),holder.first_list_item_iv);

        return convertView;
    }

    class ViewHolder{

        ImageView first_list_item_iv;
        TextView first_list_item_title;
        TextView first_list_item_desc;
        TextView first_list_item_publishTime;

    }


}
