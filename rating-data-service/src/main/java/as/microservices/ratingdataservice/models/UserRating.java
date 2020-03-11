package as.microservices.ratingdataservice.models;

import java.util.ArrayList;
import java.util.List;

public class UserRating {

    private List<Rating> userRatings = new ArrayList<>();

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }
}
