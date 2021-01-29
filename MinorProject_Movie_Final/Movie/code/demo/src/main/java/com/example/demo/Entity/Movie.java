package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public Integer Id; /*primary key */

    private String name;
    private String genre;



    /*
    *
    * Justification for relation ship. : One movie can have many reviews so one to many relation ship.
    * cascade = CascadeType.ALL : this will be use full in the movie deletion. this will delete all the items related to the movie. i.e when the movie is
    * deleted all the reviews, casts , link related to that movie will be deleted.
    * */
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL) /*Making the Review class as the owning side -->
    In the Review table an movie_id column will be created.In that foreign key of movie is maintained. */
    private List<Review> reviews;


    /*
     *
     * Justification for relation ship. : One movie can have many casts and one cast can be part of many movies
     *                                    so many to many relation ship.
     *
     * Movie class is the owning side of cast . for an many to many relation ship an association table will be
     * created with the name move_casts in that two columns will be created with name move_id and cast_id which will
     * stores the respective primary keys in the respective columns which is know as foreign keys
     *
     * */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="movie_cast",joinColumns = @JoinColumn(name="movie_id"),inverseJoinColumns = @JoinColumn(name="cast_id"))
    private List<Cast> casts = new ArrayList<>();



    /*
     *
     * Justification for relation ship. : One movie only one link one to one relation ship.
     *
     * we are making the streaming class is an owning side of the movie  so that in the streaming_details table
     * an column is created with name movie_link_id which will maintain the foreign of the movie
     *
     *
     * */
    @OneToOne(mappedBy = "movie_link",cascade = CascadeType.ALL)
    private StreamingDetails streamingDetails;


    private Date releaseDate;
    private String duration;
    private String language;


    public Movie() {
    }

    public Integer getId() {
        return Id;
    }

    /*public void setId(Integer id) {
        Id = id;
    }*/

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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public StreamingDetails getStreamingDetails() {
        return streamingDetails;
    }

    public void setStreamingDetails(StreamingDetails streamingDetails) {
        this.streamingDetails = streamingDetails;
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

    @Override
    public String toString() {
        return "Movie{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", reviews=" + reviews +
                ", casts=" + casts +
                ", streamingDetails=" + streamingDetails +
                ", releaseDate=" + releaseDate +
                ", duration='" + duration + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
