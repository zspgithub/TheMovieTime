package com.l000phone.themovietime.shop.shopAdapter;

import android.content.Context;
import android.graphics.Color;
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
import com.l000phone.themovietime.shop.shopDataParse.ShopPriceDataParse;
import com.l000phone.themovietime.shop.shopbean.Category;
import com.l000phone.themovietime.shop.shopbean.CategoryItem;
import com.l000phone.themovietime.shop.shopbean.Price;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  
 */
public class ShopFirstListviewAdapter extends BaseAdapter {
    private List<Category> list;
    private LayoutInflater inflater;
    private Context context;
    private List<Map<String,Object>> catitem;
    private List<Map<String,Object>> prices = new ArrayList<>();
    private Map<String,Object> map;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what){
                case 21:
                   Price price = (Price) msg.obj;
                    String s = price.getMinSalePrice().toString();
                    double v = Double.parseDouble(s);
                    viewHolder1.tvbottommomeyone.setText("¥" + (v / 100));

                    break;

                case 22:
                    Price price1 = (Price) msg.obj;
                    String s1 = price1.getMinSalePrice().toString();
                    double v1 = Double.parseDouble(s1);
                    viewHolder1.tvbottommomeytwo.setText("¥" + (v1 / 100));

                    break;

                case 23:
                    Price price2 = (Price) msg.obj;
                    String s2 = price2.getMinSalePrice().toString();
                    double v2 = Double.parseDouble(s2);
                    viewHolder1.tvbottommomeythree.setText("¥" + (v2 / 100));

                    break;
            }
        }
    };
    private ViewHolder viewHolder1;
    private List<CategoryItem> categoryItems;
    private Handler idHandler;
    private String a;
    private String b;
    private String c;
    private String ca;


    public ShopFirstListviewAdapter(List<Category> list, Context context,List<Map<String,Object>> catitem,final Handler idHandler) {
        this.list = list;
        this.context = context;
        this.catitem = catitem;
        this.idHandler = idHandler;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(catitem!= null){
            ret = catitem.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return catitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View ret = null ;
        if(convertView != null){
            ret = convertView;
        }else{
            ret = inflater.inflate(R.layout.shop_listview_item, null);
        }
        viewHolder1 = (ViewHolder) ret.getTag();
        if(viewHolder1 == null){
            viewHolder1 = new ViewHolder();
            viewHolder1.listMore = (TextView) ret.findViewById(R.id.shop_listview_item_tv_more);
            viewHolder1.color = (TextView) ret.findViewById(R.id.shop_listview_item_color);
            viewHolder1.title = (TextView) ret.findViewById(R.id.shop_listview_item_title);
            viewHolder1.imgtop = (ImageView) ret.findViewById(R.id.shop_listview_item_topimg);
            viewHolder1.imgbottomone = (ImageView) ret.findViewById(R.id.shop_listview_item_bottom_imgone);
            viewHolder1.imgbottotwo = (ImageView) ret.findViewById(R.id.shop_listview_item_bottom_imgtwo);
            viewHolder1.imgbottothree = (ImageView) ret.findViewById(R.id.shop_listview_item_bottom_imgthree);
            viewHolder1.tvbottomnameone = (TextView) ret.findViewById(R.id.shop_listview_item_bottom_tv_nameone);
            viewHolder1.tvbottomnametwo = (TextView) ret.findViewById(R.id.shop_listview_item_bottom_tv_nametwo);
            viewHolder1.tvbottomnamethree = (TextView) ret.findViewById(R.id.shop_listview_item_bottom_tv_namethree);
            viewHolder1.tvbottommomeyone = (TextView) ret.findViewById(R.id.shop_listview_item_bottom_tv_moneyone);
            viewHolder1.tvbottommomeytwo = (TextView) ret.findViewById(R.id.shop_listview_item_bottom_tv_moneytwo);
            viewHolder1.tvbottommomeythree = (TextView) ret.findViewById(R.id.shop_listview_item_bottom_tv_moneythree);
            ret.setTag(viewHolder1);
        }else {
            viewHolder1 = (ViewHolder) convertView.getTag();
        }
        Category category = list.get(position);

        ca = category.getImageUrl();

        categoryItems = (List<CategoryItem>) catitem.get(position).get("item");
        ;

        viewHolder1.color.setBackgroundColor(Color.parseColor(category.getColorValue()));

        viewHolder1.title.setText(category.getName().toString());

        viewHolder1.tvbottomnameone.setText(categoryItems.get(0).getTitle());
        viewHolder1.tvbottomnametwo.setText(categoryItems.get(1).getTitle());
        viewHolder1.tvbottomnamethree.setText(categoryItems.get(2).getTitle());

        ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHolder1.imgtop, category.getImage());

        ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHolder1.imgbottomone, categoryItems.get(0).getImage());

        ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHolder1.imgbottotwo, categoryItems.get(1).getImage());

        ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHolder1.imgbottothree, categoryItems.get(2).getImage());


        a = categoryItems.get(0).getGoodsId();
        ShopPriceDataParse.parse(a, handler);

        b = categoryItems.get(1).getGoodsId();
        ShopPriceDataParse.parse2(b, handler);

        c = categoryItems.get(2).getGoodsId();
        ShopPriceDataParse.parse3(c, handler);





        viewHolder1.imgtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String b =  ca.substring(ca.lastIndexOf("=")+1,ca.length());

                try {
                    String decode = URLDecoder.decode(b, "UTF-8");

                    Message message1 = new Message();

                    message1.what = 93;

                    message1.obj = decode;

                    idHandler.sendMessage(message1);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        });


        viewHolder1.imgbottomone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CategoryItem> ca = (List<CategoryItem>) catitem.get(position).get("item");
                String id = ca.get(0).getGoodsId();
                sendMessage(id);

            }
        });
        viewHolder1.imgbottotwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CategoryItem> ca = (List<CategoryItem>) catitem.get(position).get("item");
                String id = ca.get(1).getGoodsId();
                sendMessage(id);
            }
        });

        viewHolder1.imgbottothree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CategoryItem> ca = (List<CategoryItem>) catitem.get(position).get("item");
                String id = ca.get(2).getGoodsId();
                sendMessage(id);
            }
        });

        viewHolder1.listMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return ret;
    }

    private void sendMessage(String id){

        Message message = new Message();

        message.what = 91;

        message.obj = id;

        idHandler.sendMessage(message);
    }
    class ViewHolder{
        private TextView color;
        private TextView title;
        private ImageView imgtop;
        private ImageView imgbottomone;
        private ImageView imgbottotwo;
        private ImageView imgbottothree;
        private TextView tvbottomnameone;
        private TextView tvbottomnametwo;
        private TextView tvbottomnamethree;
        private TextView tvbottommomeyone;
        private TextView tvbottommomeytwo;
        private TextView tvbottommomeythree;
        private TextView listMore;
    }


}
