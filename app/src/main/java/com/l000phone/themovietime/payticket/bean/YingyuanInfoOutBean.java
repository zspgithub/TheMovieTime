package com.l000phone.themovietime.payticket.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class YingyuanInfoOutBean {
    private String name;
    private String address;
    private PayticketYingyuanFeatureBean feature;
    private List<PayticketYingyuanFirstBean> couponActivityList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PayticketYingyuanFeatureBean getFeature() {
        return feature;
    }

    public void setFeature(PayticketYingyuanFeatureBean feature) {
        this.feature = feature;
    }

    public List<PayticketYingyuanFirstBean> getCouponActivityList() {
        return couponActivityList;
    }

    public void setCouponActivityList(List<PayticketYingyuanFirstBean> couponActivityList) {
        this.couponActivityList = couponActivityList;
    }
}
