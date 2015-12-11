package com.l000phone.themovietime.discover.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.bean.TopListListView;

import java.util.List;

/**
 * Created by Administrator on 15-11-19.
 */
public class TopListListViewAdapter extends BaseAdapter {
    private Context context;
    private List<TopListListView> list;
    private LayoutInflater mInflater;
    public TopListListViewAdapter(Context context,List<TopListListView>list){
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
            convertView=mInflater.inflate(R.layout.discover_toplist_item,null);
            holder=new ViewHolder();
            holder.topListNameCn= (TextView) convertView.findViewById(R.id.topList_item_topListNameCn);
            holder.summary= (TextView) convertView.findViewById(R.id.topList_item_summary);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        TopListListView listListView=list.get(position);
        holder.topListNameCn.setText(listListView.getTopListNameCn());
        holder.summary.setText(listListView.getSummary());
        return convertView;
    }

    public  class ViewHolder{
        TextView topListNameCn;
        TextView summary;
    }

}
