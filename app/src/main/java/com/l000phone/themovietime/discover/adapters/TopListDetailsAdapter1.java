package com.l000phone.themovietime.discover.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.bean.TopListDetailsBean1;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 15-11-19.
 */
public class TopListDetailsAdapter1 extends BaseAdapter{
    private Context context;
    private LayoutInflater mInflater;
    private List<TopListDetailsBean1>list;
    public TopListDetailsAdapter1(Context context,List<TopListDetailsBean1>list){
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
            convertView=mInflater.inflate(R.layout.discover_toplistdetails_listview_item0,null);
            holder=new ViewHolder();
            holder.rankNum= (TextView) convertView.findViewById(R.id.topListDetails_item0_rankNum);
            holder.name= (TextView) convertView.findViewById(R.id.topListDetails_item0_name);
            holder.rating= (TextView) convertView.findViewById(R.id.topListDetails_item0_rating);
            holder.nameEn= (TextView) convertView.findViewById(R.id.topListDetails_item0_nameEn);
            holder.director= (TextView) convertView.findViewById(R.id.topListDetails_item0_director);
            holder.actor= (TextView) convertView.findViewById(R.id.topListDetails_item0_actor);
            holder.releaseDate= (TextView) convertView.findViewById(R.id.topListDetails_item0_releaseDate);
            holder.releaseLocation= (TextView) convertView.findViewById(R.id.topListDetails_item0_releaseLocation);
            holder.remark= (TextView) convertView.findViewById(R.id.topListDetails_item0_remark);
            holder.image= (ImageView) convertView.findViewById(R.id.topListDetails_item0_image);
            convertView.setTag(holder);

        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        TopListDetailsBean1 bean1=list.get(position);
        holder.rankNum.setText(bean1.getRankNum());
        holder.name.setText(bean1.getName());
        holder.rating.setText(bean1.getRating());
        holder.nameEn.setText(bean1.getNameEn());
        holder.director.setText(bean1.getDirector());
        holder.actor.setText(bean1.getActor());
        holder.releaseDate.setText(bean1.getReleaseDate());
        holder.releaseLocation.setText(bean1.getReleaseLocation());
        holder.remark.setText(bean1.getRemark());
        BitmapUtils bitmapUtils =new BitmapUtils(context);
        bitmapUtils.display(holder.image,bean1.getPosterUrl());
        return convertView;
    }

    public class ViewHolder{
        TextView rankNum;
        TextView name;
        TextView rating;
        TextView nameEn;
        TextView director;
        TextView actor;
        TextView releaseDate;
        TextView releaseLocation;
        TextView remark;
        ImageView image;
    }
}
