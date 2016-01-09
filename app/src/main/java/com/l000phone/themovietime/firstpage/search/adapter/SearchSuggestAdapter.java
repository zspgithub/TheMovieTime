package com.l000phone.themovietime.firstpage.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.firstpage.search.searchbean.SearchSuggestBean;
import com.l000phone.themovietime.utils.ImageUtils;

import java.util.List;

/**
 * 搜索建议适配器类
 *
 * 
 */
public class SearchSuggestAdapter extends BaseAdapter {

    private Context context;
    private List<SearchSuggestBean> list;
    private ImageUtils imageUtils = new ImageUtils();

    public SearchSuggestAdapter(Context context, List<SearchSuggestBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.first_search_suggest_item,null);
            holder.first_search_sgt_iv = (ImageView) convertView.findViewById(R.id.first_search_sgt_iv);
            holder.first_search_sgt_titlecn = (TextView) convertView.findViewById(R.id.first_search_sgt_titlecn);
            holder.first_search_sgt_year = (TextView) convertView.findViewById(R.id.first_search_sgt_year);
            holder.first_search_sgt_titleen = (TextView) convertView.findViewById(R.id.first_search_sgt_titleen);
            holder.first_search_sgt_contentType = (TextView) convertView.findViewById(R.id.first_search_sgt_contentType);
            holder.first_search_sgt_movieType = (TextView) convertView.findViewById(R.id.first_search_sgt_movieType);
            holder.first_search_sgt_rLocation = (TextView) convertView.findViewById(R.id.first_search_sgt_rLocation);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        SearchSuggestBean bean = list.get(position);
        holder.first_search_sgt_titlecn.setText(bean.getTitlecn());
        holder.first_search_sgt_year.setText("("+bean.getYear()+")");
        holder.first_search_sgt_titleen.setText(bean.getTitleen());
        holder.first_search_sgt_contentType.setText(bean.getContentType());
        holder.first_search_sgt_movieType.setText(bean.getMovieType());
        holder.first_search_sgt_rLocation.setText(bean.getrLocation());
        imageUtils.loadImage(bean.getCover(),holder.first_search_sgt_iv);

        return convertView;
    }

    class ViewHolder{

        private ImageView first_search_sgt_iv;
        private TextView first_search_sgt_titlecn;
        private TextView first_search_sgt_year;
        private TextView first_search_sgt_titleen;
        private TextView first_search_sgt_contentType;
        private TextView first_search_sgt_movieType;
        private TextView first_search_sgt_rLocation;

    }

}
