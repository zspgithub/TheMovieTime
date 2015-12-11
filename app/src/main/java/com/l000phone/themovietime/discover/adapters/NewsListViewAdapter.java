package com.l000phone.themovietime.discover.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.l000phone.themovietime.R;
import com.l000phone.themovietime.discover.bean.NewsListView;
import com.l000phone.themovietime.discover.bean.NewsListViewImages;
import com.lidroid.xutils.BitmapUtils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-11-16.
 */
public class NewsListViewAdapter extends BaseAdapter {
    private Context context;
    private List<NewsListView> newsListViews;
    private LayoutInflater mInflater;
    final  int TYPE_1=1;
    final  int TYPE_2=2;
    final  int TYPE_3=3;
    public NewsListViewAdapter(Context context, List<NewsListView> newsListViews) {
        this.context = context;
        this.newsListViews = newsListViews;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        //Log.d("main",newsListViews.size()+"++++++++++++++++++++++++++");
        int i=0;
        if (newsListViews.size()!=0){
            return newsListViews.size();
        }
        return i;
    }

    @Override
    public Object getItem(int position) {
        return newsListViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
         if(newsListViews.get(position).getType().equals("1")){
            return TYPE_2;
        }
        return TYPE_1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        List<ImageView> imageViews = new ArrayList<ImageView>();
        ViewHolder holder = null;
        ViewHolder1 holder1 = null;
        //String type = newsListViews.get(position).getType();
        int type=getItemViewType(position);

        if (convertView == null) {

            switch (type) {
                case TYPE_1:
                    convertView = mInflater.inflate(R.layout.discover_news_listview_item1,null);
                    holder = new ViewHolder();
                    holder.image1 = (ImageView) convertView.findViewById(R.id.news_item1_image);
                    holder.title1 = (TextView) convertView.findViewById(R.id.news_item1_title1);
                    holder.title2 = (TextView) convertView.findViewById(R.id.news_item1_title2);
                    holder.publishTime = (TextView) convertView.findViewById(R.id.news_item1_publishTime);
                    holder.commentCount = (TextView) convertView.findViewById(R.id.news_item1_commentCount);
                    holder.tag = (TextView) convertView.findViewById(R.id.news_item1_tag);
                    convertView.setTag(holder);
                    break;
                case TYPE_2:
                    convertView = mInflater.inflate(R.layout.discover_news_listview_item2, null);
                    holder1 = new ViewHolder1();
                    holder1.title1 = (TextView) convertView.findViewById(R.id.news_item2_title);
                    holder1.image1 = (ImageView) convertView.findViewById(R.id.news_item2_image1);
                    holder1.image2 = (ImageView) convertView.findViewById(R.id.news_item2_image2);
                    holder1.image3 = (ImageView) convertView.findViewById(R.id.news_item2_image3);
                    holder1.publishTime = (TextView) convertView.findViewById(R.id.news_item2_publishTime);
                    holder1.commentCount = (TextView) convertView.findViewById(R.id.news_item2_commentCount);
                    imageViews.add(holder1.image1);
                    imageViews.add(holder1.image2);
                    imageViews.add(holder1.image3);
                    convertView.setTag(holder1);
                    break;

            }
        } else {
            switch (type) {
                case TYPE_1:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case TYPE_2:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
            }
        }

        switch (type) {
            case TYPE_1:
                NewsListView news = newsListViews.get(position);
                holder.title1.setText(news.getTitle());
                holder.title2.setText(news.getTitle2());
                //设置时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = format.format(new Date(System.currentTimeMillis() - Long.valueOf(news.getPublishTime())));
                holder.publishTime.setText(date);
                //设置评论人数
                holder.commentCount.setText("评论 " + news.getCommentCount());

                holder.image1.setTag(news.getImage());
                BitmapUtils bitmapUtils = new BitmapUtils(context);
                bitmapUtils.display(holder.image1, (String) holder.image1.getTag());

                String tags=news.getTag();
                if (tags.equals("推荐")){
                    holder.tag.setVisibility(View.VISIBLE);
                }
                break;
            case TYPE_2:
                NewsListView news1 = newsListViews.get(position);

                holder1.title1.setText(news1.getTitle());
              //  设置时间
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date1 = format1.format(new Date(System.currentTimeMillis() - Long.valueOf(news1.getPublishTime())));
                holder1.publishTime.setText(date1);
                //Log.d("test",news1.getPublishTime());
                //               设置图片

//                String typeAgain=news1.getType();
//                if (typeAgain.equals("1")&&news1.getImages().size()==3){
//                    List<NewsListViewImages> images = news1.getImages();
//                    BitmapUtils bitmapUtils1 = new BitmapUtils(context);
//                    for (int i = 0; i < images.size(); i++) {
//                        bitmapUtils1.display(imageViews.get(i), images.get(i).getUrl1());
//                    }
//                }

                holder1.commentCount.setText("评论 "+news1.getCommentCount());
                break;
        }

        return convertView;
    }

    public  class ViewHolder {
        ImageView image1;

        TextView title1;
        TextView title2;
        TextView publishTime;
        TextView commentCount;
        TextView tag;
    }
    public  class ViewHolder1{
        ImageView image1;
        ImageView image2;
        ImageView image3;
        TextView title1;
        TextView publishTime;
        TextView commentCount;
    }

}




