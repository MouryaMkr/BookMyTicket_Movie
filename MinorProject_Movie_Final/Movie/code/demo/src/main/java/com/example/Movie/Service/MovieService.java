package com.example.Movie.Service;

import com.example.Movie.Entity.Cast;
import com.example.Movie.Entity.Movie;
import com.example.Movie.Entity.Review;
import com.example.Movie.Entity.StreamingDetails;
import com.example.Movie.Model.CastRequest;
import com.example.Movie.Model.MovieRequest;
import com.example.Movie.Model.ReviewRequest;
import com.example.Movie.Repository.CastsRepository;
import com.example.Movie.Repository.MovieRepository;
import com.example.Movie.Repository.ReviewRepository;
import com.example.Movie.Repository.StreamingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovieService implements MovieInterFace
{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    StreamingDetailsRepository streamingDetailsRepository;

    @Autowired
    CastsRepository castsRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public void Create(MovieRequest movieRequest)
    {
        if(movieRequest != null)
        {

            Movie movie = new Movie();
            StreamingDetails streamingDetails = new StreamingDetails();
            Cast cast;

            /*Movie details*/
            movie.setName(movieRequest.getName());
            movie.setLanguage(movieRequest.getLanguage());
            movie.setGenre(movieRequest.getGenre());
            movie.setReleaseDate(movieRequest.getReleaseDate());
            movie.setDuration(movieRequest.getDuration());
            movie.setPrice(movieRequest.getPrice());

            /*Streaming Details*/
            streamingDetails.setLink(movieRequest.getStreamingDetailsRequest().getLink());
            streamingDetails.setMovie_link(movie);

            /*Casts Details*/
            for (CastRequest castRequest : movieRequest.getCasts())
            {
                cast = new Cast();
                cast.setName(castRequest.getName());
                cast.setRole(castRequest.getRole());
                movie.getCasts().add(cast);
                /*saving the details to data base.*/
                castsRepository.save(cast);
            }

            /*Reviews --> This should add by the user */

            /*saving the details to data base.*/
            movieRepository.save(movie);
            streamingDetailsRepository.save(streamingDetails);

        }
        else
        {
            System.out.println("Received movie request is null");
        }
    }

    @Override
    public Movie getMovieFromDB(Integer id)
    {
        return movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id did not exist"+id));
    }

    @Override
    public void Update(Integer id,MovieRequest movieRequest)
    {
        Movie movie =  movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id did not exist"+id));
    }

    @Override
    public void deleteMovieFromDB(Integer id)
    {
        Movie movie =  movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Id did not exist"+id));
        movieRepository.delete(movie);
    }


    public List<Movie> getAllMovieFromDB()
    {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        return movies;
    }

    public void addReview(String movieName, ReviewRequest reviewRequest)
    {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        Integer movieId = 0;
        Review review;

        if(movies != null)
        {
            /*Adding the review for movie*/

            for (Movie movie : movies)
            {
                if(movieName.equals(movie.getName()))
                {
                    review = new Review();
                    review.setRating(reviewRequest.getRating());
                    review.setComment(reviewRequest.getComment());
                    review.setMovie(movie);
                    reviewRepository.save(review);
                }
            }
        }
        else
        {
            System.out.println("NULL While trying to add review");
        }
    }

    @Override
    public void addPriceToMovie(Movie movie, Double price)
    {
        movie.setPrice(price);
        movieRepository.save(movie);
    }


    public void deleteMovieByName(List<Movie> movies)
    {
        for (Movie movie : movies)
        {
            movieRepository.deleteById(movie.getId());
        }
    }
}
