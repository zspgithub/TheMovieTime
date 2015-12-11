package com.l000phone.themovietime.shop.shopAdapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.network.ShopfirstBitmapNetwork;
import com.l000phone.themovietime.shop.shopbean.GoodsList;

import java.util.List;

/**
 * Created by bfc on 2015/11/17.
 */
public class GridBottomAdapter extends BaseAdapter{

    private List<GoodsList> lists;
    private Context context;
    private LayoutInflater inflater;
    private Handler handler;

    public GridBottomAdapter(List<GoodsList> lists, Context context,Handler handler) {
        this.lists = lists;
        this.handler = handler;
        this.context = context;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(lists!=null){
            ret = lists.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View ret = null;
        if(convertView!=null){
            ret = convertView;
        }else {
            ret =LayoutInflater.from(context).inflate(R.layout.shopbottomgriditem,parent,false);
        }
        ViewHoder viewHoder = (ViewHoder) ret.getTag();
        if(viewHoder == null){

            viewHoder = new ViewHoder();
            viewHoder.imageView = (ImageView) ret.findViewById(R.id.grid_bottom_img);
            viewHoder.name = (TextView) ret.findViewById(R.id.grid_bottom_tvname);
            viewHoder.price = (TextView) ret.findViewById(R.id.grid_bottom_tvprice);
            ret.setTag(viewHoder);
        }else {
            viewHoder = (ViewHoder) ret.getTag();
        }
        final GoodsList goodsList = lists.get(position);

        viewHoder.name.setText(goodsList.getName());

        viewHoder.price.setText("¥"+(Integer.parseInt(goodsList.getMinSalePrice())/100)+".0");

        ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHoder.imageView, goodsList.getImage());

        viewHoder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = goodsList.getGoodsId();

                Message message1 = new Message();

                message1.what = 92;

                message1.obj = id;

                handler.sendMessage(message1);
            }
        });



        return ret;
    }

    class ViewHoder{
        private ImageView imageView;
        private TextView name;
        private TextView price;
    }

}
