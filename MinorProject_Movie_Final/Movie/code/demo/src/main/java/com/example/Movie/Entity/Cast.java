package com.example.Movie.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cast")
public class Cast
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer Id;

    private String name;
    private String role;

    /*
    *
    *
    * Mapping Justification : one movie can have many casts so ManyToMany mapping
    *
    * Movie class is the owning side of cast . for an many to many relation ship an association table will be
    * created with the name move_casts in that two columns will be created with name move_id and cast_id which will
    * stores the respective primary keys in the respective columns which is know as foreign keys
    *
    *
    * */

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Movie> movie;

    public Cast() {
    }

    public Cast(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", movie=" + movie +
                '}';
    }
}
