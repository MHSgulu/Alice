package com.uw.alice.data.model;

public class MovieRankingList {

    private String movieRankingName;
    private String tips;
    private int moviePoster;

    public MovieRankingList(String movieRankingName, String tips, int moviePoster) {
        this.movieRankingName = movieRankingName;
        this.tips = tips;
        this.moviePoster = moviePoster;
    }


    public String getMovieRankingName() {
        return movieRankingName;
    }

    public void setMovieRankingName(String movieRankingName) {
        this.movieRankingName = movieRankingName;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public int getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(int moviePoster) {
        this.moviePoster = moviePoster;
    }
}
