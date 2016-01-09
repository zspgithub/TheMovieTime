package com.l000phone.themovietime.payticket.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.bean.YingyuanInfoBean;
import com.l000phone.themovietime.utils.ImageUtils;

import java.util.List;

/**
 * 
 */
public class YingyuanRecyclerAdapter extends RecyclerView.Adapter<YingyuanRecyclerAdapter.ViewHolder> {
    private List<YingyuanInfoBean> list;
    private Context context;

    public interface OnItemClickLitener
    {

        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public YingyuanRecyclerAdapter(List<YingyuanInfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yingyuan_info_recyclerview_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        YingyuanInfoBean infoBean = list.get(position);
        holder.movieName.setText(infoBean.getTitle());
        ImageUtils imageUtils = new ImageUtils();
        imageUtils.loadImage(infoBean.getImg(),holder.imageView);
        if (mOnItemClickLitener!=null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.imageView,pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView movieName;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.yingyuan_info_recyclerView_item_movieName);
            imageView = (ImageView) itemView.findViewById(R.id.yingyuan_info_recyclerView_item_image);
        }
    }
}
