package com.l000phone.themovietime.shop.shopAdapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.network.ShopfirstBitmapNetwork;
import com.l000phone.themovietime.shop.shopbean.Price;
import com.l000phone.themovietime.shop.shopbean.SubList;

import java.util.List;
import java.util.Map;

/**
 *  
 */
public class GridCenterAdapter extends BaseAdapter {

    private List<SubList> list;
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String,String>> listId;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;

            switch (what){

                case 24:
                   Price price = (Price) msg.obj;

                    String minSalePrice = price.getMinSalePrice();

                    double parseDouble = Double.parseDouble(minSalePrice);
                    String p = "¥"+(parseDouble/100);
                    Log.d("BU",p);
                    viewHolder.price.setText(p);

                    break;
            }
        }
    };
    private ViewHolder viewHolder;
    private Handler idHandler;

    public GridCenterAdapter(List<SubList> list, Context context,final Handler idHandler) {
        this.list = list;
        this.context = context;
        this.idHandler = idHandler;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        int ret = 0;
        if (list!=null){
            ret = list.size();
        }
        return ret;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View ret = null;
        if(convertView != null){
            ret = convertView;
        }else {
            ret = inflater.inflate(R.layout.shopcentergriditem,parent,false);
        }
        viewHolder = (ViewHolder) ret.getTag();
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            
            viewHolder.imageView = (ImageView) ret.findViewById(R.id.grid_center_img);
            viewHolder.name = (TextView) ret.findViewById(R.id.grid_center_tvname);
            viewHolder.price = (TextView) ret.findViewById(R.id.grid_center_tvprice);
            ret.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) ret.getTag();
        }


        final SubList subListitem = list.get(position);

        viewHolder.name.setText(subListitem.getTitle());

        ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHolder.imageView, subListitem.getImage());


        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = subListitem.getGoodsId();

                Message messageid = new Message();

                messageid.what = 90;

                messageid.obj = id;

                idHandler.sendMessage(messageid);

            }
        });


        return ret;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView name;
        private TextView price;
    }
}
