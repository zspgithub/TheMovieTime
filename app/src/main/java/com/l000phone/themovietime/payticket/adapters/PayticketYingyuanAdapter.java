package com.l000phone.themovietime.payticket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.bean.PayticketYingyuanBean;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 
 */
public class PayticketYingyuanAdapter extends BaseAdapter {
    private List<PayticketYingyuanBean> list;
    private Context context;
    private LayoutInflater mInflater;

    public PayticketYingyuanAdapter(List list, Context context) {
        this.list = list;
        this.context = context;
        mInflater =LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.payticket_yingyuan_list_item,null);
            vh = new ViewHolder();
            vh.yingyuanName = ((TextView) convertView.findViewById(R.id.payticket_yingyuan_item_name));
            vh.yingyuanAdress = ((TextView) convertView.findViewById(R.id.payticket_yingyuan_item_address));
            vh.price = ((TextView) convertView.findViewById(R.id.payticket_yingyuan_item_money));
            vh.dianqing = ((TextView) convertView.findViewById(R.id.payticket_yingyuan_item_dianqing));
            vh.iconImax  = ((ImageView) convertView.findViewById(R.id.payticket_yingyuan_item_icon_Imax));
            vh.iconP = ((ImageView) convertView.findViewById(R.id.payticket_yingyuan_item_icon_P));
            vh.iconVip = ((ImageView) convertView.findViewById(R.id.payticket_yingyuan_item_icon_vip));
            vh.iconWifi  = ((ImageView) convertView.findViewById(R.id.payticket_yingyuan_item_icon_wifi));
            vh.iconNear = ((ImageView) convertView.findViewById(R.id.payticket_yingyuan_item_nearImg));
            vh.line = ((TextView) convertView.findViewById(R.id.payticket_yingyuan_item_line));
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        PayticketYingyuanBean yingyuanBean  = list.get(position);
        vh.yingyuanName.setText(yingyuanBean.getCinameName());
        vh.yingyuanAdress.setText(yingyuanBean.getAddress());
        vh.price.setText(""+Integer.parseInt(yingyuanBean.getMinPrice())/100);
        if (position==0){
            vh.line.setVisibility(View.VISIBLE);
            vh.iconNear.setVisibility(View.VISIBLE);
        }else {
            vh.line.setVisibility(View.GONE);
            vh.iconNear.setVisibility(View.GONE);
        }
        if (yingyuanBean.getFeature().getHasIMAX().equals("0")){
            vh.iconImax.setVisibility(View.GONE);
        }else {
            vh.iconImax.setVisibility(View.VISIBLE);
        }
        if (yingyuanBean.getFeature().getHasVIP().equals("0")){
            vh.iconVip.setVisibility(View.GONE);
        }else {
            vh.iconVip.setVisibility(View.VISIBLE);
        }
        if (yingyuanBean.getFeature().getHasPark().equals("0")){
            vh.iconP.setVisibility(View.GONE);
        }else {
            vh.iconP.setVisibility(View.VISIBLE);
        }
        if (yingyuanBean.getFeature().getHasWifi().equals("0")){
            vh.iconWifi.setVisibility(View.GONE);
        }else {
            vh.iconWifi.setVisibility(View.VISIBLE);
        }
        if (yingyuanBean.getCouponActivityList().size()==1){
            vh.dianqing.setVisibility(View.VISIBLE);
        }else {
            vh.dianqing.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder{
        TextView yingyuanName;
        TextView yingyuanAdress;
        TextView price;
        TextView dianqing;
        ImageView iconVip;
        ImageView iconP;
        ImageView iconWifi;
        ImageView iconImax;
        ImageView iconNear;
        TextView line;
    }
}
