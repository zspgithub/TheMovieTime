package com.l000phone.themovietime.discover.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.bean.TopListDetailsBean2;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * 
 */
public class TopListDetailsAdapter2 extends BaseAdapter{
    private Context context;
    private List<TopListDetailsBean2>list;
    private LayoutInflater mInflater;

    public TopListDetailsAdapter2(Context context,List<TopListDetailsBean2>list){
        this.context=context;
        this.list=list;
        mInflater=LayoutInflater.from(context);

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
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.discover_toplistdetails_listview_item2,null);
            holder=new ViewHolder();
            holder.birthDay= (TextView) convertView.findViewById(R.id.topListDetails_item2_birthday);
            holder.birthLocation= (TextView) convertView.findViewById(R.id.topListDetails_item2_birthLocation);
            holder.nameCn= (TextView) convertView.findViewById(R.id.topListDetails_item2_nameCn);
            holder.nameEn= (TextView) convertView.findViewById(R.id.topListDetails_item2_nameEn);
            holder.rankNum= (TextView) convertView.findViewById(R.id.topListDetails_item2_rankNum);
            holder.rating= (TextView) convertView.findViewById(R.id.topListDetails_item2_rating);
            holder.sex= (TextView) convertView.findViewById(R.id.topListDetails_item2_sex);
            holder.summary= (TextView) convertView.findViewById(R.id.topListDetails_item2_summary);
            holder.image= (ImageView) convertView.findViewById(R.id.topListDetails_item2_image);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        TopListDetailsBean2 bean2=new TopListDetailsBean2();
        holder.birthDay.setText(bean2.getBirthDay());
        holder.birthLocation.setText(bean2.getBirthLocation());
        holder.nameCn.setText(bean2.getNameCn());
        holder.nameEn.setText(bean2.getNameEn());
        holder.rankNum.setText(bean2.getRankNum());
        holder.rating.setText(bean2.getRating());
        holder.sex.setText(bean2.getSex());
        holder.summary.setText(bean2.getSummary());
        BitmapUtils bitmapUtils=new BitmapUtils(context);
        bitmapUtils.display(holder.image,bean2.getPosterUrl());
        return convertView;
    }

    public  class ViewHolder{
        TextView birthDay;
        TextView birthLocation;
        TextView nameCn;
        TextView nameEn;
        TextView rankNum;
        TextView rating;
        TextView sex;
        TextView summary;
        ImageView image;
    }
}
