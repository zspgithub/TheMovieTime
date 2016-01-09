package com.l000phone.themovietime.firstpage.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.l000phone.themovietime.R;
import java.util.List;

/**
 * 自定义RecyclerAdapter，给Recycler填充内容
 *
 * 
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private LayoutInflater mInflater;
    private List<Bitmap> list;
    private ItemClickCallback mItemCallback;

    public RecyclerAdapter(Context context, List<Bitmap> list){

        mInflater = LayoutInflater.from(context);
        this.list = list;

    }

    public void setItemClickCallback(ItemClickCallback itemCallback){

        mItemCallback = itemCallback;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.first_recycler,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.imageView = (ImageView) view.findViewById(R.id.first_recycler_img);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.imageView.setImageBitmap(list.get(position));

       if (mItemCallback != null){

           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   mItemCallback.onChange(holder.itemView,position);

               }
           });
       }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }

        ImageView imageView;

    }

    //Item点击回调
    public interface ItemClickCallback{

        public void onChange(View view, int position);

    }

}
