package com.l000phone.themovietime.firstpage.bean;

import java.util.List;

/**
 * 这是首页上部的bean类
 *
 * 
 */
public class FirstTopBean {

    private String totalHotMovie;
    private String totalComingMovie;
    private String totalCinemaCount;
    private String count;

    private List<FirstMovieBean> movies;

    public String getTotalHotMovie() {
        return totalHotMovie;
    }

    public void setTotalHotMovie(String totalHotMovie) {
        this.totalHotMovie = totalHotMovie;
    }

    public String getTotalComingMovie() {
        return totalComingMovie;
    }

    public void setTotalComingMovie(String totalComingMovie) {
        this.totalComingMovie = totalComingMovie;
    }

    public String getTotalCinemaCount() {
        return totalCinemaCount;
    }

    public void setTotalCinemaCount(String totalCinemaCount) {
        this.totalCinemaCount = totalCinemaCount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<FirstMovieBean> getMovies() {
        return movies;
    }

    public void setMovies(List<FirstMovieBean> movies) {
        this.movies = movies;
    }
}
