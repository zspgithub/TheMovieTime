package com.l000phone.themovietime.payticket.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.payticket.bean.PayticketShangyingBean;
import com.l000phone.themovietime.utils.ImageUtils;
import com.lidroid.xutils.BitmapUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 
 */
public class PayTicketShangyingListAdapter extends BaseAdapter {
    private List<PayticketShangyingBean> list;
    private Context context;
    private LayoutInflater mInflater;

    public PayTicketShangyingListAdapter(List<PayticketShangyingBean> list, Context context) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView==null){
            convertView = mInflater.inflate(R.layout.payticket_shangying_list_item,null);
            vh = new ViewHolder();
            vh.title = ((TextView) convertView.findViewById(R.id.payticke_shangying_listView_item_title));
            vh.day = ((TextView) convertView.findViewById(R.id.payticke_shangying_listView_item_day));
            vh.count = ((TextView) convertView.findViewById(R.id.payticke_shangying_listView_item_count));
            vh.type  = ((TextView) convertView.findViewById(R.id.payticke_shangying_listView_item_type));
            vh.daoyan = ((TextView) convertView.findViewById(R.id.payticke_shangying_listView_item_daoyan));
            vh.yushou = ((TextView) convertView.findViewById(R.id.payticket_shangying_listView_item_yushou));
            vh.yugao = ((TextView) convertView.findViewById(R.id.payticket_shangying_listView_item_yugao));
            vh.movieIcon = ((ImageView) convertView.findViewById(R.id.payticke_shangying_listView_item_movieIcon));
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        PayticketShangyingBean shangyingBean = list.get(position);
        vh.day.setText(shangyingBean.getrDay()+"日");
        vh.title.setText(shangyingBean.getTitle());
        vh.count.setText(shangyingBean.getWantedCount());
        vh.type.setText("人想看-"+shangyingBean.getType()+"/"+shangyingBean.getLocationName());
        vh.daoyan.setText("导演：" + shangyingBean.getDirector());
//        ImageUtils imageUtils = new ImageUtils();
//        imageUtils.loadImage(shangyingBean.getImage(),vh.movieIcon);
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.display(vh.movieIcon,shangyingBean.getImage());
        if (shangyingBean.getVideoCount().equals("0")){
            vh.yugao.setVisibility(View.GONE);
        }else {
            vh.yugao.setVisibility(View.VISIBLE);
        }
        if (shangyingBean.getIsTicket().equals("false")){
            vh.yushou.setText("上映提醒");
            vh.yushou.setTextColor(Color.GREEN);
            vh.yushou.setBackgroundResource(R.drawable.payticket_reying_zhiyingxun_bg);
        }else {
            vh.yushou.setText("超前预售");
            vh.yushou.setTextColor(Color.WHITE);
            vh.yushou.setBackgroundResource(R.drawable.payticket_reying_yushou_bg);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView movieIcon;
        TextView day;
        TextView title;
        TextView count;
        TextView type;
        TextView daoyan;
        TextView yushou;
        TextView yugao;
    }
}
