package com.l000phone.themovietime.shop.shopAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.network.ShopfirstBitmapNetwork;
import com.l000phone.themovietime.shop.shopbuybean.LotsgoodsList;

import java.util.List;

/**
 * 
 */
public class ShopLotsGridviewAdapter extends BaseAdapter {

    private List<LotsgoodsList> lists;
    private Context context;
    private LayoutInflater inflater;

    public ShopLotsGridviewAdapter(List<LotsgoodsList> lists, Context context) {
        this.lists = lists;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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
            ret = inflater.inflate(R.layout.shop_buy_lots_griditem,parent,false);
        }
        ViewHolder viewHolder = (ViewHolder) ret.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolder();

            viewHolder.tvlijian = (TextView) ret.findViewById(R.id.shop_buy_lots_grid_item_lijian);
            viewHolder.imageView = (ImageView) ret.findViewById(R.id.shop_buy_lots_grid_item_imgview);
            viewHolder.name = (TextView) ret.findViewById(R.id.shop_buy_lots_grid_item_tvName);
            viewHolder.price = (TextView) ret.findViewById(R.id.shop_buy_lots_grid_item_tvPrice);
            viewHolder.oldPrice = (TextView) ret.findViewById(R.id.shop_buy_lots_grid_item_tvOldprice);
            viewHolder.linearLayout = (LinearLayout) ret.findViewById(R.id.allname);
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();

            ret.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) ret.getTag();
        }
        final LotsgoodsList lotsgoodsList = lists.get(position);
        String iconText = lotsgoodsList.getIconText();
        if("立减".equals(iconText)){
            viewHolder.tvlijian.setText(iconText);
            viewHolder.tvlijian.setBackgroundColor(Color.parseColor(lotsgoodsList.getBackground()));
        }else {
            viewHolder.tvlijian.setVisibility(View.GONE);
        }

        ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHolder.imageView, lotsgoodsList.getImageSrc());

        viewHolder.name.setText(lotsgoodsList.getName());

        double price = Double.parseDouble(lotsgoodsList.getMinSalePrice());
        viewHolder.price.setText("¥" + price / 100);
        double oldPrice = Double.parseDouble(lotsgoodsList.getMarketPrice());
        viewHolder.oldPrice.setText("¥"+oldPrice/100);

        return ret;
    }

    class ViewHolder{
        private TextView tvlijian;
        private ImageView imageView;
        private TextView name;
        private TextView price;
        private TextView oldPrice;
        private LinearLayout linearLayout;
    }
}
