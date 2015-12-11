 package com.l000phone.themovietime.payticket.bean;

/**
 * Created by Administrator on 2015/11/18.
 */
public class YingyuanInfoBean {
    private String movieId;
    private String title;
    private String img;
    private String[] showDates;
    private String length;
    private String type;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String[] getShowDates() {
        return showDates;
    }

    public void setShowDates(String[] showDates) {
        this.showDates = showDates;
    }
}
