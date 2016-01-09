package com.l000phone.themovietime.payticket.bean;

import java.util.List;

/**
 *  
 */
public class PayticketYingyuanBean {
    private String cinameName;
    private String address;
    private String isTicket;
    private String cinemaId;
    private String minPrice;
    private PayticketYingyuanFeatureBean feature;
    private List<PayticketYingyuanFirstBean> couponActivityList;

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

    public String getCinameName() {
        return cinameName;
    }

    public void setCinameName(String cinameName) {
        this.cinameName = cinameName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsTicket() {
        return isTicket;
    }

    public void setIsTicket(String isTicket) {
        this.isTicket = isTicket;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }
}
