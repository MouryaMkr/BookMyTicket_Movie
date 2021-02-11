package com.example.Movie.Model;

public class ReviewRequest {

    private Double rating;
    private String comment;

    public ReviewRequest() {
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "rating='" + rating + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
