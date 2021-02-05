package com.uw.alice.data.entity;

public class MovieEntity {
    private String movieName;
    private double movieRating;
    private String moviePosterUrl;

    public MovieEntity(String movieName, double movieRating, String moviePosterUrl) {
        this.movieName = movieName;
        this.movieRating = movieRating;
        this.moviePosterUrl = moviePosterUrl;
    }


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(double movieRating) {
        this.movieRating = movieRating;
    }

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }

    public void setMoviePosterUrl(String moviePosterUrl) {
        this.moviePosterUrl = moviePosterUrl;
    }
}
