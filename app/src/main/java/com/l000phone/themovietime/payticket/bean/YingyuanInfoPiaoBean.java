package com.l000phone.themovietime.payticket.bean;

/**
 * 
 */
public class YingyuanInfoPiaoBean {
    private String versionDesc;
    private String language;
    private String cinemaPrice;
    private String price;
    private String hall;
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCinemaPrice() {
        return cinemaPrice;
    }

    public void setCinemaPrice(String cinemaPrice) {
        this.cinemaPrice = cinemaPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }
}
