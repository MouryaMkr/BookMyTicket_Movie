package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "streaming_details")
public class StreamingDetails
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer Id;


    /*
     *
     * Justification for relation ship. : One link is related to only one movie. so OneToOne relation ship.
     *
     * we are making the streaming class is an owning side of the movie  so that in the streaming_details table
     * an column is created with name movie_link_id which will maintain the foreign of the movie
     *
     *
     * */
    @OneToOne
    @JsonIgnore //this will ignores the serilization and deserilization part.
    private Movie movie_link;

    private String link;

    public StreamingDetails() {
    }

    public Movie getMovie_link() {
        return movie_link;
    }

    public void setMovie_link(Movie movie_link) {
        this.movie_link = movie_link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "StreamingDetails{" +
                "Id=" + Id +
                ", movie_link=" + movie_link +
                ", link='" + link + '\'' +
                '}';
    }
}
