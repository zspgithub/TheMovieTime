package com.l000phone.themovietime.payticket.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.bean.PayticketShangyingBean;
import com.l000phone.themovietime.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class PayticketShangyingRecycleAdapter extends RecyclerView.Adapter<PayticketShangyingRecycleAdapter.ViewHolder>{
    private List<PayticketShangyingBean> attention = new ArrayList<>();
    private Context context;

    public PayticketShangyingRecycleAdapter(List<PayticketShangyingBean> attention, Context context) {
        this.attention = attention;
        this.context = context;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payticket_shangying_recycleview_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PayticketShangyingBean shangyingBean = attention.get(position);

        //TODO  赋值
        holder.time.setText(shangyingBean.getrMonth()+"月"+shangyingBean.getrDay()+"日");
        holder.title.setText(shangyingBean.getTitle());
        holder.count.setText(shangyingBean.getWantedCount());
        holder.type.setText("人想看-"+shangyingBean.getType()+"/"+shangyingBean.getLocationName());
        holder.daoyan.setText("导演："+shangyingBean.getDirector());
        holder.yanyuan.setText("演员："+shangyingBean.getActor1()+shangyingBean.getActor2());
        ImageUtils imageUtils = new ImageUtils();
        imageUtils.loadImage(shangyingBean.getImage(),holder.imageView);
        if (shangyingBean.getIsTicket().equals("true")){
            holder.yushou.setVisibility(View.VISIBLE);
        }else {
            holder.yushou.setVisibility(View.GONE);
        }
        if (mOnItemClickLitener!=null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = shangyingBean.getId();
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.imageView, pos);
                }
            });
        }
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return attention.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView time;
        TextView title;
        TextView count;
        TextView type;
        TextView daoyan;
        TextView yanyuan;
        TextView yushou;
        TextView yugao;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = ((ImageView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_movieIcon));
            time = ((TextView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_time));
            title = ((TextView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_moviename));
            count = ((TextView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_count));
            type = ((TextView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_movieType));
            daoyan = ((TextView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_daoyan));
            yanyuan = ((TextView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_yanyuan));
            yushou = ((TextView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_yushou));
            yugao = ((TextView) itemView.findViewById(R.id.payticket_shangying_top_recycleView_item_yugao));
        }
    }
}
