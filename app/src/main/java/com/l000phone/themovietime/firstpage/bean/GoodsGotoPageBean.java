package com.l000phone.themovietime.firstpage.bean;

/**
 * 首页中间商品详情Bean类
 *
 * Created by Administrator on 15-11-15.
 */
public class GoodsGotoPageBean {

    private String gotoType;
    private String url;
    private String parameters;
    private String relatedTypeUrl;

    public String getGotoType() {
        return gotoType;
    }

    public void setGotoType(String gotoType) {
        this.gotoType = gotoType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getRelatedTypeUrl() {
        return relatedTypeUrl;
    }

    public void setRelatedTypeUrl(String relatedTypeUrl) {
        this.relatedTypeUrl = relatedTypeUrl;
    }
}
