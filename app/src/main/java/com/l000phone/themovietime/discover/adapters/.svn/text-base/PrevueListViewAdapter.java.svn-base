package com.l000phone.themovietime.discover.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.bean.PrevueListView;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 15-11-18.
 */
public class PrevueListViewAdapter extends BaseAdapter {
    private List<PrevueListView>listViews;
    private Context context;
    private LayoutInflater mInflater;

    public PrevueListViewAdapter(Context context,List<PrevueListView>listViews){
        this.context=context;
        this.listViews=listViews;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listViews.size();
    }

    @Override
    public Object getItem(int position) {
        return listViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.discover_prevue_listview_item,null);
            holder=new ViewHolder();
            holder.coverImg= (ImageView) convertView.findViewById(R.id.prevue_item_image);
            holder.movieName= (TextView) convertView.findViewById(R.id.prevue_item_movieName);
            holder.summary= (TextView) convertView.findViewById(R.id.prevue_item_summary);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        PrevueListView view=listViews.get(position);
        holder.movieName.setText(view.getMovieName());
        holder.summary.setText(view.getSummary());
        BitmapUtils bitmapUtils=new BitmapUtils(context);
        bitmapUtils.display(holder.coverImg,view.getCoverImg());
        return convertView;
    }

    public class ViewHolder{
        TextView movieName;
        TextView summary;
        ImageView coverImg;
    }
}
