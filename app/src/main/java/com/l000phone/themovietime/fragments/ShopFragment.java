package com.l000phone.themovietime.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.l000phone.themovietime.MainActivity;
import com.l000phone.themovietime.R;
import com.l000phone.themovietime.shop.network.ShopfirstBitmapNetwork;
import com.l000phone.themovietime.shop.shopActivity.ShopBuyActivity;
import com.l000phone.themovietime.shop.shopActivity.ShopBuyLotsGridActivity;
import com.l000phone.themovietime.shop.shopActivity.ShopSerchActivity;
import com.l000phone.themovietime.shop.shopActivity.ShopWebActivity;
import com.l000phone.themovietime.shop.shopActivity.Shop_gouwucheActivity;
import com.l000phone.themovietime.shop.shopAdapter.GridBottomAdapter;
import com.l000phone.themovietime.shop.shopAdapter.GridCenterAdapter;
import com.l000phone.themovietime.shop.shopAdapter.ShopFirstListviewAdapter;
import com.l000phone.themovietime.shop.shopAdapter.ShopfirstViewPagerAdapter;
import com.l000phone.themovietime.shop.shopDataParse.ShopBottomDataParse;
import com.l000phone.themovietime.shop.shopDataParse.ShopFirstDataParse;
import com.l000phone.themovietime.shop.shopbean.Category;
import com.l000phone.themovietime.shop.shopbean.CellA;
import com.l000phone.themovietime.shop.shopbean.CellB;
import com.l000phone.themovietime.shop.shopbean.CellC;
import com.l000phone.themovietime.shop.shopbean.GoodsList;
import com.l000phone.themovietime.shop.shopbean.NavigatorFirthIcon;
import com.l000phone.themovietime.shop.shopbean.NavigatorIcon;
import com.l000phone.themovietime.shop.shopbean.Scroll;
import com.l000phone.themovietime.shop.shopbean.SubList;
import com.l000phone.themovietime.shop.shopbean.Topic;
import com.l000phone.themovietime.shop.utils.MyGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment implements View.OnClickListener{


    private ViewPager viewPager;
    private ImageView imageView;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private List<ImageView> img;
    private TextView wanju;
    private TextView shuma;
    private TextView fushi;
    private TextView jiaju;
    private List<NavigatorIcon> navigatorIcons;
    private ImageView imgwanju;
    private ImageView imgshuma;
    private ImageView imgfushi;
    private ImageView imgjiaju;
    private List<ImageView> navigImage;
    private int index = 0;
    private final int SCROLL_WHAT = 88;
    private ImageView icon[];
    private NavigatorFirthIcon navigatorFirthIcons;
    private Handler handler02 = new MyHandle();
    private Handler handler = new Handler() {
        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            //获取返回的联网解析数据
            switch (what){
                //返回1时，是scoll;
                case 1:
                    scolllist = (List<Scroll>) msg.obj;
                    String url = null;
                    //循环获取scoll里的图片的内容
                    for (int i = 0; i < scolllist.size() ; i++) {
                        url = scolllist.get(i).getImage();
                        ShopfirstBitmapNetwork.bitMapUtils(getActivity(), img.get(i), url, handler);
                    }
                    break;
                //返回2时是NavigatorIcon；
                case 2:
                    navigatorIcons = (List<NavigatorIcon>) msg.obj;
                    String url1 = null;
                    for (int i = 0; i < navigatorIcons.size() ; i++) {
                        url1 = navigatorIcons.get(i).getImage();
                        ShopfirstBitmapNetwork.navigBitMapUtils(getActivity(), navigImage.get(i),url1,handler);
                    }
                    break;

                //返回3时是navigatorFirthIcons

                case 3:
                    navigatorFirthIcons = (NavigatorFirthIcon) msg.obj;

                    ShopfirstBitmapNetwork.navigatorFirthBitMapUtils(getActivity(),imgjiaju, navigatorFirthIcons.getImg1(),handler);

                    break;
                //返回4是cellA
                case 4:
                    cellA = (CellA) msg.obj;
                    ShopfirstBitmapNetwork.CellABitMapUtils(getActivity(), imgcellA, cellA.getImg());
                    break;
                //返回5是cellB
                case 5:
                    cellB = (CellB) msg.obj;
                    ShopfirstBitmapNetwork.CellABitMapUtils(getActivity(), imgcellB, cellB.getImg());
                    break;
                //返回6是cellC
                case 6:
                    cellCs = (List<CellC>) msg.obj;

                    String urlcellc01 = cellCs.get(0).getImage().toString();
                    String urlcellc02 = cellCs.get(1).getImage().toString();

                    if (null!=urlcellc01){

                   ShopfirstBitmapNetwork.CellCBitMapUtils(getActivity(),imgcellC01
                   ,urlcellc01,imgcellC02,urlcellc02);
                    }
                    break;
                //返回7是topic
                case 7:

                    listopt = (List<Map<String, Object>>) msg.obj;

                    list701 = (List<Topic>) listopt.get(0).get("topic");
                    int size = list701.size();
                    for (int i = 0; i < size; i++) {
                        String uncheckImage = list701.get(i).getUncheckImage();
                        ShopfirstBitmapNetwork.CellABitMapUtils(getActivity(),listRgImg.get(i),uncheckImage);
                    }
                    String Urlcheck = list701.get(indexScoll).getCheckedImage();
                    String background = list701.get(indexScoll).getBackgroupImage();
                    ShopfirstBitmapNetwork.CellCBitMapUtils(getActivity(), listRgImg.get(indexScoll), Urlcheck, rgimgBack, background);
                    listopyitem = (List<Map<String, Object>>) listopt.get(1).get("topic");
                    rgimgtvEGName.setText(list701.get(indexScoll).getTitleEn());
                    rgimgtvCNName.setText(list701.get(indexScoll).getTitleCn());
                    List<SubList> s1 = (List<SubList>) listopyitem.get(indexScoll).get("sublist");

                    sub = new ArrayList<>();
                    sub.addAll(s1);

                    scollGridadpter = new GridCenterAdapter(sub,getActivity(),handler);

                    centerGridView.setAdapter(scollGridadpter);
                    break;
                //返回8是category，即listview里的内容
                case 8 :
                    List<Map<String,Object>> listCat = (List<Map<String, Object>>) msg.obj;
                    List<Category> categories = (List<Category>) listCat.get(0).get("category");
                    List<Map<String,Object>> item = (List<Map<String, Object>>) listCat.get(1).get("category");
                    ShopFirstListviewAdapter listviewAdapter = new ShopFirstListviewAdapter(categories,getActivity(),item,handler);

                    listView.setAdapter(listviewAdapter);
                    break;

                //返回11是Scoll里的图片的解析
                case 11:
                    ShopfirstViewPagerAdapter adapter = new ShopfirstViewPagerAdapter(img,scolllist,handler);
                    viewPager.setAdapter(adapter);
                    addViewPagerLisnter();
                    handler02.sendEmptyMessageDelayed(SCROLL_WHAT, 3000);
                    break;

                //返回12是NavigatorIcon里的图片解析
                case 12:
                    wanju.setText(navigatorIcons.get(0).getIconTitle());
                    shuma.setText(navigatorIcons.get(1).getIconTitle());
                    fushi.setText(navigatorIcons.get(2).getIconTitle());
                    break;

                //返回13是navigatorFirthIcons的图片解析

                case 13:
                    jiaju.setText(navigatorFirthIcons.getIconTitle1());
                    break;
                //返回90接收centerGridviewAdapter的click事件的ID来跳转
                case 90:

                    String idGrid = (String) msg.obj;

                    changActivity(idGrid);
                    break;

                //返回91是接收listviewAdapter的click事件的Id来跳转
                case 91:

                    String listviewId = (String) msg.obj;

                    changActivity(listviewId);
                    break;

                //返回92是接收bottomGridviewAdpater的click事件的ID来跳转
                case 92:

                    String bottomGridId = (String) msg.obj;

                    changActivity(bottomGridId);
                    break;

                //返回93是listview的item上部的大图片点击的跳转事件
                case  93:

                    String name = (String) msg.obj;

                    Intent intent = new Intent(getActivity(), ShopBuyLotsGridActivity.class);

                    Bundle bundle = new Bundle();

                    bundle.putString("name",name);

                    bundle.putString("categoryId1","0");

                    intent.putExtras(bundle);

                    startActivity(intent);

                    break;

                //返回94是viewpager的点击事件得到Url的跳转
                case 94:

                    String viewpagerUrl = (String) msg.obj;

                    Intent viewPagerIntent = new Intent(getActivity(), ShopWebActivity.class);

                    Bundle bundle1 = new Bundle();

                    bundle1.putString("url",viewpagerUrl);

                    viewPagerIntent.putExtras(bundle1);

                    startActivity(viewPagerIntent);

                    break;
            }
        }
    };
    private ImageView imgcellA;
    private ImageView imgcellB;
    private ImageView imgcellC01;
    private ImageView imgcellC02;
    private View layout;
    private ListView listView;
    private ImageView centerBackImg;
    private ImageView centerScollImg01;
    private ImageView centerScollImg02;
    private ImageView centerScollImg03;
    private ImageView centerScollImg04;
    private ImageView centerScollImg05;
    private TextView centerEngilshtv;
    private TextView centerChinesetv;
    private List<ImageView> scollImg;
    private RadioGroup radioGroup;
    private RecyclerView recyclerView;
    private List<ImageView> recycleImg;
    private MyGridView bottomGridview;

    private Handler handlerbottom = new MyHandle(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;

            switch (what){

                case 1:
                    //返回解析得到的goosList集合
                   List<GoodsList> goodsLists = (List<GoodsList>) msg.obj;

                    GridBottomAdapter adapter = new GridBottomAdapter(goodsLists,getContext(),handler);

                    bottomGridview.setAdapter(adapter);

                    break;
            }
        }
    };
    private View gridBottom;
    private MyGridView centerGridView;
    private List<ImageView> listRgImg;
    private ImageView rgimgBack;
    private TextView rgimgtvEGName;
    private TextView rgimgtvCNName;
    private HorizontalScrollView scrollView;
    private int mScreenWitdh;
    private RadioGroup radioGroup1;
    private int indexScoll = 2;
    private List<SubList> sub;
    private List<Map<String, Object>> listopyitem;
    private List<Map<String, Object>> listopt;
    private GridCenterAdapter scollGridadpter;
    private MainActivity activity;
    private TextView listMore;
    private ImageView rgimg01;
    private ImageView rgimg02;
    private ImageView rgimg03;
    private ImageView rgimg04;
    private ImageView rgimg05;
    private List<Topic> list701;
    private View include;
    private List<CellC> cellCs;
    private CellB cellB;
    private CellA cellA;
    private List<Scroll> scolllist;
    private TextView topTextview;
    private ImageView gouwuche;
    private ImageView saoyisao;

    private void addViewPagerLisnter() {
        //setViewpageLisnter过时了，突然就过时了。。，用addViewPagerLinsnter
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < icon.length; i++) {
                    icon[i].setImageResource(R.mipmap.dot_black);
                }
                icon[position].setImageResource(R.mipmap.dot_white);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = ((MainActivity) context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_shop, container, false);
        initView(ret);
        return ret;
    }

    private void initView(View ret) {
        //初始化view,先找到listview
        listView = (ListView) ret.findViewById(R.id.shop_first_listview);
        //找到添加到listview上部的布局，转变成view，并加到listview的上部
        //注意这里，只有listview的adapter有内容显示时添加的部分才会显示
        layout = LayoutInflater.from(getActivity()).inflate(R.layout.shop_first_add,null,false);
       //找到listView 下部的布局，转变成view，并加入到listview的底部
        gridBottom = LayoutInflater.from(getActivity()).inflate(R.layout.shop_bottom_gridview,null,false);



        listView.addHeaderView(layout);
        listView.addFooterView(gridBottom);

        //通过添加进来的view找到里面的控件，初始化数据,找的时候要通过这个layout
        topTextview = (TextView) ret.findViewById(R.id.shop_top_tvsearch);
        gouwuche = (ImageView) ret.findViewById(R.id.shop_top_imgagouwuche);
        saoyisao = (ImageView) ret.findViewById(R.id.shop_top_imgSaoyisao);
        include = ret.findViewById(R.id.shop_top_add_selech);
        viewPager = ((ViewPager) layout.findViewById(R.id.shop_top_viewpager));
        wanju = (TextView) layout.findViewById(R.id.shop_top_wanju);
        shuma = (TextView) layout.findViewById(R.id.shop_top_shuma);
        fushi = (TextView) layout.findViewById(R.id.shop_top_fushi);
        jiaju = (TextView) layout.findViewById(R.id.shop_top_jiaju);
        imgwanju = (ImageView) layout.findViewById(R.id.shop_top_image_wanju);
        imgshuma = (ImageView) layout.findViewById(R.id.shop_top_image_shuma);
        imgfushi = (ImageView) layout.findViewById(R.id.shop_top_image_fushi);
        imgjiaju = (ImageView) layout.findViewById(R.id.shop_top_image_jiaju);
        imgcellA = (ImageView) layout.findViewById(R.id.shop_center_imagcallA);
        imgcellB = (ImageView) layout.findViewById(R.id.shop_center_imagcallB);
        imgcellC01 = (ImageView) layout.findViewById(R.id.shop_center_imagcallC01);
        imgcellC02 = (ImageView) layout.findViewById(R.id.shop_center_imagcallC02);
        centerGridView = (MyGridView) layout.findViewById(R.id.shop_center_gridView);
        bottomGridview = (MyGridView) gridBottom.findViewById(R.id.shop_bottom_gridview2);
        rgimg01 = (ImageView) layout.findViewById(R.id.shop_center_rg_img01);
        rgimg02 = (ImageView) layout.findViewById(R.id.shop_center_rg_img02);
        rgimg03 = (ImageView) layout.findViewById(R.id.shop_center_rg_img03);
        rgimg04 = (ImageView) layout.findViewById(R.id.shop_center_rg_img04);
        rgimg05 = (ImageView) layout.findViewById(R.id.shop_center_rg_img05);
        scrollView = (HorizontalScrollView) layout.findViewById(R.id.shop_addview_bottom_oriscollview);
        rgimgtvEGName = (TextView) layout.findViewById(R.id.shop_addview_bottom_tv_englishName);
        rgimgtvCNName = (TextView) layout.findViewById(R.id.shop_addview_bottom_tv_chineseName);
        rgimgBack = (ImageView) layout.findViewById(R.id.shop_addview_bottom_topimgback);
        listRgImg = new ArrayList<>();
        listRgImg.add(rgimg01);
        listRgImg.add(rgimg02);
        listRgImg.add(rgimg03);
        listRgImg.add(rgimg04);
        listRgImg.add(rgimg05);
        ImageView recycleImg01  = new ImageView(getActivity());
        ImageView recycleImg02  = new ImageView(getActivity());
        ImageView recycleImg03  = new ImageView(getActivity());
        ImageView recycleImg04  = new ImageView(getActivity());
        ImageView recycleImg05  = new ImageView(getActivity());
        recycleImg = new ArrayList<>();
        recycleImg.add(recycleImg01);
        recycleImg.add(recycleImg02);
        recycleImg.add(recycleImg03);
        recycleImg.add(recycleImg04);
        recycleImg.add(recycleImg05);


        centerEngilshtv = (TextView) layout.findViewById(R.id.shop_addview_bottom_tv_englishName);
        centerChinesetv = (TextView) layout.findViewById(R.id.shop_addview_bottom_tv_chineseName);
        navigImage = new ArrayList<>();
        navigImage.add(imgwanju);
        navigImage.add(imgshuma);
        navigImage.add(imgfushi);
        navigImage.add(imgjiaju);
        //准备viewpager所需要的图片资源的初始化
        initImage();
        //初始化小圆点
        initDot(layout);
        //联网请求数据
        ShopFirstDataParse.parse(handler);
        ShopBottomDataParse.parse(handlerbottom);
        initLinstner();
    }

    private void initLinstner() {


        imgwanju.setOnClickListener(this);
        imgshuma.setOnClickListener(this);
        imgfushi.setOnClickListener(this);
        imgjiaju.setOnClickListener(this);
        imgcellA.setOnClickListener(this);
        imgcellB.setOnClickListener(this);
        imgcellC01.setOnClickListener(this);
        imgcellC02.setOnClickListener(this);
        rgimg01.setOnClickListener(this);
        rgimg02.setOnClickListener(this);
        rgimg03.setOnClickListener(this);
        rgimg04.setOnClickListener(this);
        rgimg05.setOnClickListener(this);
        topTextview.setOnClickListener(this);
        gouwuche.setOnClickListener(this);
        saoyisao.setOnClickListener(this);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(270, 0);
            }
        });


        viewPager.post(new Runnable() {
            @Override
            public void run() {
                int[] position = new int[2];

                viewPager.getLocationOnScreen(position);

            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                int[] position = new int[2];

                viewPager.getLocationOnScreen(position);

                int i = position[1];

                if (-200 < i) {
                    include.setBackgroundResource(android.R.color.transparent);
                } else if (-200 > i) {
                    include.setBackgroundResource(R.color.myblue);
                }


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }



    private void initDot(View layout) {
        //找到小圆点所在linealayout
        LinearLayout linearLayout = (LinearLayout) layout.findViewById(R.id.shop_viewpager_dot);

        icon = new ImageView[img.size()];

        int len = icon.length;
        for (int i = 0; i < len; i++) {
            icon[i] = (ImageView) linearLayout.getChildAt(i);
            icon[i].setTag(i);
        }

        icon[0].setImageResource(R.mipmap.dot_white);
    }

    private void initImage() {
        //new 3个viewpager所需要的ImageView
        imageView1 = new ImageView(getActivity());
        imageView2 = new ImageView(getActivity());
        imageView3 = new ImageView(getActivity());
        //设置图片为占满空间
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        img = new ArrayList<>();
        img.add(imageView1);
        img.add(imageView2);
        img.add(imageView3);
    }

    private void viewChange(){
        //设置当index小于3时自动切换，大于2时，就是换了3张，应该从第一张开始
                int a = index++;
                if(a < 3){
                    handler02.removeMessages(SCROLL_WHAT);
                    viewPager.setCurrentItem(a);
                }else  if(a > 2){
                    handler02.removeMessages(SCROLL_WHAT);
                    index = 0;
                    a = index;
                    viewPager.setCurrentItem(a);
                }
        //隔3秒发一次来实现切换
                handler02.sendEmptyMessageDelayed(SCROLL_WHAT, 3000);

    }

    @Override
    public void onClick(View v) {

       switch (v.getId()){
           case R.id.shop_top_image_wanju:
               String url1 = navigatorIcons.get(0).getUrl();
               String categoryId1 = url1.substring(url1.lastIndexOf("=")+1, url1.length());
               gotoLotsActivity(categoryId1);

               break;
           case R.id.shop_top_image_shuma:
               String url2 = navigatorIcons.get(1).getUrl();
               String categoryId2 = url2.substring(url2.lastIndexOf("=")+1, url2.length());
               gotoLotsActivity(categoryId2);
               break;

           case R.id.shop_top_image_fushi:
               String url3 = navigatorIcons.get(2).getUrl();
               String categoryId3 = url3.substring(url3.lastIndexOf("=")+1, url3.length());
               gotoLotsActivity(categoryId3);
               break;

           case R.id.shop_top_image_jiaju:
               String url4 =  navigatorFirthIcons.getUrl();
               String categoryId4 = url4.substring(url4.lastIndexOf("=") + 1, url4.length());
               gotoLotsActivity(categoryId4);

               break;

           case R.id.shop_center_imagcallA:
               String goodsId = cellA.getGoodsId();
               changActivity(goodsId);
               break;
           case R.id.shop_center_imagcallC01:
               String url = cellCs.get(0).getUrl();
               String id = url.substring(url.lastIndexOf("/") - 6, url.lastIndexOf("/"));
              changActivity(id);
               break;

           case R.id.shop_center_imagcallC02:

               String urlc02 = cellCs.get(1).getUrl();
               String idc02 = urlc02.substring(urlc02.lastIndexOf("/") - 6, urlc02.lastIndexOf("/"));
               changActivity(idc02);
               break;

           case R.id.shop_center_imagcallB:
               String url5 = cellB.getUrl();
               Intent intent = new Intent(getActivity(),ShopWebActivity.class);
               Bundle bundle = new Bundle();
               bundle.putString("url",url5);
               intent.putExtras(bundle);
               startActivity(intent);
               break;
           case R.id.shop_center_rg_img01:
               scollChanged(0);
               break;
           case R.id.shop_center_rg_img02:
               scollChanged(1);
               break;

           case R.id.shop_center_rg_img03:
               scollChanged(2);
               break;

           case R.id.shop_center_rg_img04:
               scollChanged(3);
               break;

           case R.id.shop_center_rg_img05:
               scollChanged(4);
               break;

           case R.id.shop_top_tvsearch:
               Intent intent1 = new Intent(getActivity(), ShopSerchActivity.class);
               startActivity(intent1);
               break;

           case R.id.shop_top_imgSaoyisao:

               break;

           case R.id.shop_top_imgagouwuche:
               Intent intent2 = new Intent(getActivity(), Shop_gouwucheActivity.class);
               startActivity(intent2);
               break;

       }
    }

    private void scollChanged(int ind){
        String uncheckUrl = list701.get(indexScoll).getUncheckImage();
        ShopfirstBitmapNetwork.CellABitMapUtils(getActivity(),listRgImg.get(indexScoll),uncheckUrl);
        indexScoll = ind;
        List<SubList> subList1 = (List<SubList>) listopyitem.get(indexScoll).get("sublist");
        rgimgtvEGName.setText(list701.get(indexScoll).getTitleEn());
        rgimgtvCNName.setText(list701.get(indexScoll).getTitleCn());
        String Urlcheck = list701.get(indexScoll).getCheckedImage();
        String background = list701.get(indexScoll).getBackgroupImage();
        ShopfirstBitmapNetwork.CellCBitMapUtils(getActivity(), listRgImg.get(indexScoll), Urlcheck, rgimgBack, background);
        sub.clear();
        sub.addAll(subList1);
        scollGridadpter.notifyDataSetChanged();
    }

    private void changActivity(String id){
        Intent intent = new Intent(getActivity(), ShopBuyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void gotoLotsActivity(String categoryId1){
        Intent intent = new Intent(getActivity(),ShopBuyLotsGridActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name"," ");
        bundle.putString("categoryId1",categoryId1);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    class MyHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewChange();
        }
    }
}
