package com.l000phone.themovietime.payticket.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.bean.ReyingInfoActorBean;
import com.l000phone.themovietime.utils.ImageUtils;

import java.util.List;

/**
 * 
 */
public class ReyingInfoRecycleViewAdapter extends RecyclerView.Adapter<ReyingInfoRecycleViewAdapter.ViewHolder> {
    private List<ReyingInfoActorBean> list;
    private Context context;

    public ReyingInfoRecycleViewAdapter(List<ReyingInfoActorBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reyinginfo_recycleview_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReyingInfoActorBean actorBean = list.get(position);
        holder.name.setText(actorBean.getActor());
        holder.shiyan.setText(actorBean.getRoleName());
        ImageUtils imageUtils = new ImageUtils();
        imageUtils.loadImage(actorBean.getActorImg(),holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView shiyan;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.reying_info_yanyuanImage);
            name = (TextView) itemView.findViewById(R.id.reying_info_name);
            shiyan = (TextView) itemView.findViewById(R.id.reying_info_shi);
        }
    }
}
