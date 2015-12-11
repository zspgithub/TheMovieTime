package com.l000phone.themovietime.shop.shopAdapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.network.ShopfirstBitmapNetwork;
import com.l000phone.themovietime.shop.shopbuybean.ContentList;

import java.util.List;
import java.util.Map;

/**
 * Created by bfc on 2015/11/18.
 */
public class ShopBuyListviewAdapter extends BaseAdapter {

    private List<ContentList> listcontentlist;
    private List<String> listgoods;
    private Context context;
    private LayoutInflater inflater;
    private int index;
    private List<String> second;

    public ShopBuyListviewAdapter(List<Map<String,Object>> list, Context context) {

        this.listcontentlist = (List<ContentList>) list.get(0).get("contentlist");
        this.listgoods = (List<String>) list.get(1).get("goods");
        this.second = (List<String>) list.get(2).get("second");
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getCount() {
        int ret = 0;
        switch (index){

            case 1:
                if(listcontentlist!=null){
                    ret = listcontentlist.size();
                }
                break;

            case 2:
                if(listgoods!=null){
                    ret = listgoods.size();
                }
                break;

            case 3:
                if(second!=null){
                    ret = second.size();
                }
                break;
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {

       if(index == 2){
           return listgoods.get(position);
       }else if (index == 1){
           return listcontentlist.get(position);
       }else {
           return second.get(position);
       }

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
            ret = inflater.inflate(R.layout.shopbuylistviewitem,parent,false);
        }

        ViewHolder viewHolder = (ViewHolder) ret.getTag();
        if(viewHolder == null){
            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) ret.findViewById(R.id.shop_buy_listview_item_img);
            viewHolder.textView = (TextView) ret.findViewById(R.id.shop_buy_listview_item_textview);
            ret.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) ret.getTag();
        }

        switch (index){

            case 1:
                viewHolder.imageView.setVisibility(View.VISIBLE);
                viewHolder.textView.setVisibility(View.GONE);
                ContentList contentList = listcontentlist.get(position);

                String Url = contentList.getImage();

                ShopfirstBitmapNetwork.CellABitMapUtils(context, viewHolder.imageView, Url);
                break;

            case 2:
                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.textView.setVisibility(View.VISIBLE);

                String note = listgoods.get(0);

                viewHolder.textView.setText(Html.fromHtml(note));

                break;

            case 3:
                viewHolder.imageView.setVisibility(View.GONE);
                viewHolder.textView.setVisibility(View.VISIBLE);

                String sen = second.get(0);

                viewHolder.textView.setText(sen);

                break;
        }


        return ret;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
