package com.l000phone.themovietime.firstpage.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.search.searchbean.SearchResultBean;
import com.l000phone.themovietime.utils.ImageUtils;

import java.util.List;

/**
 * 搜索结果适配器
 *
 * 
 */
public class SearchResultAdapter extends BaseAdapter {

    private Context context;
    private List<SearchResultBean> list;
    private ImageUtils imageUtils = new ImageUtils();

    public SearchResultAdapter(Context context, List<SearchResultBean> list) {
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
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.first_search_result_item,null);
            holder.firsr_search_rt_img = (ImageView) convertView.findViewById(R.id.firsr_search_rt_img);
            holder.first_search_rt_name = (TextView) convertView.findViewById(R.id.first_search_rt_name);
            holder.first_search_rt_year = (TextView) convertView.findViewById(R.id.first_search_rt_year);
            holder.first_search_rt_rating = (TextView) convertView.findViewById(R.id.first_search_rt_rating);
            holder.first_search_rt_nameEn = (TextView) convertView.findViewById(R.id.first_search_rt_nameEn);
            holder.first_search_rt_titleOthersCn = (TextView) convertView.findViewById(R.id.first_search_rt_titleOthersCn);
            holder.first_search_rt_movieType = (TextView) convertView.findViewById(R.id.first_search_rt_movieType);
            holder.first_search_rt_rLocation = (TextView) convertView.findViewById(R.id.first_search_rt_rLocation);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        SearchResultBean bean = list.get(position);
        holder.first_search_rt_name.setText(bean.getName());
        holder.first_search_rt_year.setText("("+bean.getYear()+")");
        holder.first_search_rt_rating.setText(bean.getRating());
        holder.first_search_rt_nameEn.setText(bean.getNameEn());
        holder.first_search_rt_movieType.setText(bean.getMovieType());
        holder.first_search_rt_rLocation.setText(bean.getrLocation());
        String[] strings = bean.getTitleOthersCn();
        String s1 = "";
        for (int i=0;i<strings.length;i++){
            s1+=strings[i];
        }
        holder.first_search_rt_titleOthersCn.setText(s1);
        imageUtils.loadImage(bean.getImg(),holder.firsr_search_rt_img);
        return convertView;
    }

    class ViewHolder{

        ImageView firsr_search_rt_img;
        TextView first_search_rt_name;
        TextView first_search_rt_year;
        TextView first_search_rt_rating;
        TextView first_search_rt_nameEn;
        TextView first_search_rt_titleOthersCn;
        TextView first_search_rt_movieType;
        TextView first_search_rt_rLocation;

    }

}
