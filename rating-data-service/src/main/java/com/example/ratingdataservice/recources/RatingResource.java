package com.example.ratingdataservice.recources;

import com.example.ratingdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/ratingdata")
public class RatingResource {

    @RequestMapping ("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        if("1".equals(movieId))
        return new Rating(movieId, 3);

        return new Rating(movieId, 5);
    }
}
