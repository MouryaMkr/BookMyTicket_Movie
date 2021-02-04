package com.example.Movie.Model;

import java.util.Date;
import java.util.List;


public class MovieRequest
{

    private String name;
    private String genre;
    private List<ReviewRequest> reviews;
    private List<CastRequest> casts;
    private StreamingDetailsRequest streamingDetailsRequest;
    private Date releaseDate;
    private String duration;
    private String language;
    private double price;

    public MovieRequest()
    {

    }

    public MovieRequest(String name, String genre, List<CastRequest> casts, StreamingDetailsRequest streamingDetails, Date releaseDate, String duration, String language) {
        this.name = name;
        this.genre = genre;
        this.casts = casts;
        this.streamingDetailsRequest = streamingDetails;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<ReviewRequest> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewRequest> reviews) {
        this.reviews = reviews;
    }

    public List<CastRequest> getCasts() {
        return casts;
    }

    public void setCasts(List<CastRequest> casts) {
        this.casts = casts;
    }

    public StreamingDetailsRequest getStreamingDetailsRequest() {
        return streamingDetailsRequest;
    }

    public void setStreamingDetailsRequest(StreamingDetailsRequest streamingDetails) {
        this.streamingDetailsRequest = streamingDetails;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "MovieRequest{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", reviews=" + reviews +
                ", casts=" + casts +
                ", streamingDetailsRequest=" + streamingDetailsRequest +
                ", releaseDate=" + releaseDate +
                ", duration='" + duration + '\'' +
                ", language='" + language + '\'' +
                ", price=" + price +
                '}';
    }
}
