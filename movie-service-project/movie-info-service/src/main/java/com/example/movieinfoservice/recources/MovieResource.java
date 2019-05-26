package com.example.movieinfoservice.recources;

import com.example.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/movie")
public class MovieResource {

    @RequestMapping ("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){

        if("1".equals(movieId))
            return new Movie(movieId, "Hitman" , "Action");

        return new Movie(movieId, "Batman", "Horror");
    }
}
