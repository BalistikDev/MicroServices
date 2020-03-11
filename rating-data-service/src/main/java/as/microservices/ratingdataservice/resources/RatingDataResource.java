package as.microservices.ratingdataservice.resources;

import as.microservices.ratingdataservice.models.Rating;
import as.microservices.ratingdataservice.models.UserRating;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {

    @RequestMapping({"/{movieId}"})
    public Rating getRatingInfo(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 5);
    }

    @RequestMapping("/user")
    public List<UserRating> getUsers(){
        List<UserRating> users = new ArrayList<>();
        UserRating user1 = new UserRating();
        UserRating user2 = new UserRating();

        List<Rating> ratings = Arrays.asList(
                new Rating("123", 4),
                new Rating("324", 6)
        );
        user1.setUserRatings(ratings);
        List<Rating> ratings2 = Arrays.asList(
                new Rating("170", 2),
                new Rating("675", 7)
        );
        user2.setUserRatings(ratings2);
        users.add(user1);
        users.add(user2);
        return users;

    }

    @RequestMapping({"/user/{userId}"})
    public UserRating getRatingUserInfo(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("123", 4),
                new Rating("200", 6)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRatings(ratings);
        return userRating;
    }
/*
     //simple get methode
    @RequestMapping("/movietest")
    public String getTestMovie(){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("http://localhost:8082/movies/test", String.class);
    }*/

//Get Custom HTTP Headers with RestTemplate
    @RequestMapping("/movietest")
    public void getTestMovie(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange("http://localhost:8082/movies/test", HttpMethod.GET, entity, String.class);

        System.out.println(result);
    }
}
