package com.example.demo.Service;

import com.example.demo.Entity.Cast;
import com.example.demo.Entity.Movie;
import com.example.demo.Entity.Review;
import com.example.demo.Entity.StreamingDetails;
import com.example.demo.Model.CastRequest;
import com.example.demo.Model.MovieRequest;
import com.example.demo.Model.ReviewRequest;
import com.example.demo.Repository.CastsRepository;
import com.example.demo.Repository.MovieRepository;
import com.example.demo.Repository.ReviewRepository;
import com.example.demo.Repository.StreamingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
}
