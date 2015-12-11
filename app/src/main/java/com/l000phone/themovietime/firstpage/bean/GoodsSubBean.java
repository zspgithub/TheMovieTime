package com.l000phone.themovietime.firstpage.bean;

/**
 * 中间商品，最上边  新品主推
 *
 * Created by Administrator on 15-11-15.
 */
public class GoodsSubBean {

    private String id;
    private String goodsId;
    private String image;
    private String title;
    private String titleColor;
    private String titleSmall;
    private String image2;
    private GoodsGotoPageBean gotoPage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTitleSmall() {
        return titleSmall;
    }

    public void setTitleSmall(String titleSmall) {
        this.titleSmall = titleSmall;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public GoodsGotoPageBean getGotoPage() {
        return gotoPage;
    }

    public void setGotoPage(GoodsGotoPageBean gotoPage) {
        this.gotoPage = gotoPage;
    }
}
