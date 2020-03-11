package as.microservices.moviecatalogservice.ressources;

import as.microservices.moviecatalogservice.models.CatalogItem;
import as.microservices.moviecatalogservice.models.Movie;
import as.microservices.moviecatalogservice.models.UserRating;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

    public MovieCatalogResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //recuperer la liste des movies regardé
        UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/user/"+userId, UserRating.class);
        //pour chaque Id de movie faire un call vers Movie Info pour recuperer les infos sur ce movie
        return ratings.getUserRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
            //put all informations together
            return new CatalogItem(movie.getOriginal_title(), movie.getOverview(), rating.getRating());
        })
        .collect(Collectors.toList());

    }
/*
    //Get URL Parameters
    @RequestMapping("/movie/{id}")
    public Movie getUrlParam(@PathVariable("id") String id){

        Map<String, String> params = new HashMap<>();
        params.put("id", id);

        Movie movie = restTemplate.getForObject("http://localhost:8082/movies/test/"+id, Movie.class, params);
        return movie;
    }
 */

/*
//test create
    @RequestMapping("/create")
    public void create(){
        Movie newMovie = new Movie("2", "Silicon valley", "Best Serie ever");
        Movie result = restTemplate.postForObject("http://localhost:8082/movies/post", newMovie, Movie.class);
        System.out.println(result);
    }

 */
}
