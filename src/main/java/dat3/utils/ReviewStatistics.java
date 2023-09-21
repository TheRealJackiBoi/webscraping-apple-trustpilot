package dat3.utils;

import dat3.model.Person;
import dat3.model.Review;

import java.util.List;

public class ReviewStatistics {

    public static double positiveReviewPercentage(List<Review> reviews) {
        return reviews.stream().filter(review -> review.getRating() > 3).count() / (double) reviews.size() * 100;
    }

    public static double neutralReviewPercentage(List<Review> reviews) {
        return reviews.stream().filter(review -> review.getRating() < 3.1 && review.getRating() > 2.9).count() / (double) reviews.size() * 100;
    }

    public static double negativeReviewPercentage(List<Review> reviews) {
        return reviews.stream().filter(review -> review.getRating() < 3).count() / (double) reviews.size() * 100;
    }

    public static double averageRating(List<Review> reviews) {
        return reviews.stream().mapToDouble(Review::getRating).average().orElse(0);
    }

    public static List<Person> mostActivePersons(List<Review> reviews) {
        return reviews.stream()
                .map(Review::getPerson)
                .distinct()
                .sorted((p1, p2) -> p2.getNumberOfReviewsOfRegistrant() - p1.getNumberOfReviewsOfRegistrant())
                .limit(3)
                .toList();
    }
}
