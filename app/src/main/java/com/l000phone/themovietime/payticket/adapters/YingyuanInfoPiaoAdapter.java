package com.l000phone.themovietime.payticket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.bean.YingyuanInfoPiaoBean;

import java.util.List;

/**
 * 
 */
public class YingyuanInfoPiaoAdapter extends BaseAdapter {
    private List<YingyuanInfoPiaoBean> list;
    private Context context;

    public YingyuanInfoPiaoAdapter(List<YingyuanInfoPiaoBean> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHolder vh = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.yingyaun_info_list_item,null);
            vh = new ViewHolder();
            vh.startTime = (TextView) convertView.findViewById(R.id.yingyuan_info_startTime);
            vh.endTime = (TextView) convertView.findViewById(R.id.yingyuan_info_endTime);
            vh.language = (TextView) convertView.findViewById(R.id.yingyuan_info_list_item_language);
            vh.price = (TextView) convertView.findViewById(R.id.yingyuan_info_list_item_price);
            vh.ting = (TextView) convertView.findViewById(R.id.yingyuan_info_list_item_ting);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        YingyuanInfoPiaoBean piaoBean = list.get(position);
        vh.ting.setText(piaoBean.getHall());
        vh.price.setText("￥"+piaoBean.getPrice());
        vh.language.setText(piaoBean.getVersionDesc()+"/"+piaoBean.getLanguage());

        return convertView;
    }
    class ViewHolder{
        TextView startTime;
        TextView endTime;
        TextView language;
        TextView ting;
        TextView price;
    }
}
