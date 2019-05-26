package com.example.moviecatalogservice.resources;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Qualifier("getRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        List<Rating> ratings = new ArrayList<>();
        Rating m1 = restTemplate.getForObject("http://rating-service/ratingdata/1" , Rating.class);
        Rating m2 = restTemplate.getForObject("http://rating-service/ratingdata/2" , Rating.class);
        ratings.add(m1);
        ratings.add(m2);

        return ratings.stream().map( rating -> {
            Movie movie1 = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);

            /*Movie movie2 = webClientBuilder.build()
                    .get()
                    .uri("http://movie-info-service/movie/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            */
            return new CatalogItem(movie1.getName(), movie1.getDescription(), rating.getRating());
            //return new CatalogItem(movie2.getName(), movie2.getDescription(), rating.getRating());
        })
        .collect(Collectors.toList());

    }
}
