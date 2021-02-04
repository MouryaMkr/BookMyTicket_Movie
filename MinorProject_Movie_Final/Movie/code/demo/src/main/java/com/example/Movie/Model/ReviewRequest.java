package com.example.Movie.Model;

public class ReviewRequest {

    private String rating;
    private String comment;

    public ReviewRequest() {
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
