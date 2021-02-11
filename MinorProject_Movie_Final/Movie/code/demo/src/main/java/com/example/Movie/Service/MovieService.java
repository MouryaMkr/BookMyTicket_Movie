package com.example.Movie.Service;

import com.example.Movie.Entity.*;
import com.example.Movie.Model.CastRequest;
import com.example.Movie.Model.MovieRequest;
import com.example.Movie.Model.ReviewRequest;
import com.example.Movie.Repository.CastsRepository;
import com.example.Movie.Repository.MovieRepository;
import com.example.Movie.Repository.ReviewRepository;
import com.example.Movie.Repository.StreamingDetailsRepository;

import com.example.Movie.RepositoryRedis.RatingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

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

    /*  Adding the reviews to the mysql data base

        added the cumulative rating to the redis

     */

    public void addReview(Movie movie, ReviewRequest reviewRequest) throws JsonProcessingException {

        Review review =  new Review();
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setMovie(movie);
        reviewRepository.save(review);  /* Saving the Review to the data base. */
        ObjectMapper objectMapper = new ObjectMapper();

        /* Adding the Cumulative review to the redis
        *
        *  First get all the reviews belongs to that movie and store it in the list.
        *
        *  Now iterate through all the reviews and get the ratings from each review and store it in the ratings list
        *
        *  Now use that ratings list to get the cumulative(OverAll) rating of the movie This task is doing by the getCumulativeRating() Fun
        *
        *  Now store that overall  rating to the redis cache.
        *
        *
        * */
        List<Review> reviewList = movie.getReviews();
        List<Double> ratings = new ArrayList<>();

        for(Review review1 : reviewList)
        {
            ratings.add(review1.getRating());
        }

        Rating_Redis rating_redis = new Rating_Redis();
        rating_redis.setMovieId(movie.getId());
        rating_redis.setRating(getCumulativeRating(ratings));

        /*Produce The Rating in the Kafka*/

        //ratingRepository.save(rating_redis);  /* This will saves the data directly to the redis cache */

        kafkaTemplate.send("Rating","Key1",objectMapper.writeValueAsString(rating_redis));
    }

    public Double getMovieRating(Integer movieId)
    {
        Double rating=0.0;
        try
        {
            rating = ratingRepository.findById(movieId).get().getRating();
            rating = Math.round(rating*10)/10.0d; //Setting the precision to 1
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rating;
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

    private Double getCumulativeRating(List<Double> ratings)
    {
        if(ratings.isEmpty())
        {
            return 0.0;
        }
        else
        {
            double cumulativeRating = 0;
            double sumOfRatings = 0;
            for (Double rating : ratings)
            {
                sumOfRatings += rating;
            }
            cumulativeRating = ( (sumOfRatings)/(ratings.size()) );

            return cumulativeRating;
        }

    }
}
