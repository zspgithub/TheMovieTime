package com.l000phone.themovietime.shop.shopAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.network.ShopfirstBitmapNetwork;
import com.l000phone.themovietime.shop.shopbuybean.Goodssss;

import java.util.List;

/**
 *  
 */
public class ShopBuyBottomGridviewAdapter extends BaseAdapter {

    private List<Goodssss> lists;
    private Context context;
    private LayoutInflater inflater;

    public ShopBuyBottomGridviewAdapter(List<Goodssss> lists, Context context) {
        this.lists = lists;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if(convertView!=null){
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.shop_buy_bottom_gridviewitem, parent, false);
        }
        ViewHolder viewHolder = (ViewHolder) ret.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolder();

            viewHolder.tvnew = (TextView) ret.findViewById(R.id.shop_buy_bottom_gridview_new);
            viewHolder.imageView = (ImageView) ret.findViewById(R.id.shop_buy_bottom_gridview_imgview);
            viewHolder.name = (TextView) ret.findViewById(R.id.shop_buy_bottom_gridview_tvName);
            viewHolder.price = (TextView) ret.findViewById(R.id.shop_buy_bottom_gridview_tvprice);
            ret.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) ret.getTag();
        }

        Goodssss goodsList = lists.get(position);
        String iconText = goodsList.getIconText();
        if("新品".equals(iconText)){
            viewHolder.tvnew.setText(iconText);
            viewHolder.tvnew.setBackgroundColor(Color.parseColor(goodsList.getBackground()));
        }else {
            viewHolder.tvnew.setVisibility(View.GONE);
        }
        viewHolder.name.setText(goodsList.getName());
        viewHolder.price.setText(goodsList.getMinSalePrice());
        ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHolder.imageView, goodsList.getImage());
        return ret;
    }

    class ViewHolder{
        private TextView tvnew;
        private ImageView imageView;
        private TextView name;
        private TextView price;
    }
}
