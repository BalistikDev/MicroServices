package as.microservices.movieinfoservice.resources;

import as.microservices.movieinfoservice.models.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    private final RestTemplate restTemplate;

    @Value("${api.key}")
    private String key;

    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{movieId}")
    public Movie GetMovieInfos(@PathVariable("movieId") String movieId){

        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + key,
                Movie.class);
        return movie;
    }

    /*
    @RequestMapping("/test")
    public String getAString(){
        return "Je suis une string test";
    }
     */

    /*
    @RequestMapping("/test/{id}")
    public ResponseEntity getMovieById(@PathVariable("id")String id){

        if (Integer.valueOf(id) <= 3){
            Movie movie = new Movie(id, "Alita", "Wait for the next one:)");
            return  new ResponseEntity<Movie>(movie, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
     */

    /*
    //test post Api
    @PostMapping("/post")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        System.out.println(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
     */
}
