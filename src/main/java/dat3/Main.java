package dat3;

import dat3.model.Review;
import dat3.utils.ReviewStatistics;
import dat3.utils.TrustpilotDataExtractor;
import dat3.utils.TrustpilotFetcher;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<Review> reviews = TrustpilotDataExtractor.extractReviews(TrustpilotFetcher.fetchReviews());

            System.out.println("Review statistics");
            System.out.println("Total number of reviews: " + reviews.size());
            System.out.println("Positive: " + ReviewStatistics.positiveReviewPercentage(reviews) + "%");
            System.out.println("Neutral: " + ReviewStatistics.neutralReviewPercentage(reviews) + "%");
            System.out.println("Negative: " + ReviewStatistics.negativeReviewPercentage(reviews) + "%");
            System.out.println("Average rating: " + ReviewStatistics.averageRating(reviews));
            System.out.println("Most active persons: " + ReviewStatistics.mostActivePersons(reviews));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}