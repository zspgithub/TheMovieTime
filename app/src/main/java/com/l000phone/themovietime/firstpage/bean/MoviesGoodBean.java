package com.l000phone.themovietime.firstpage.bean;

/**
 * 最下边每日佳片类
 *
 * 
 */
public class MoviesGoodBean {

    private String newsId;
    private String topCover;
    private String title;
    private MoviesGoodDetialBean movie;

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTopCover() {
        return topCover;
    }

    public void setTopCover(String topCover) {
        this.topCover = topCover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MoviesGoodDetialBean getMovie() {
        return movie;
    }

    public void setMovie(MoviesGoodDetialBean movie) {
        this.movie = movie;
    }
}
