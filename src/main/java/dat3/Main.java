package dat3;

import dat3.utils.TrustpilotDataExtractor;
import dat3.utils.TrustpilotFetcher;

public class Main {
    public static void main(String[] args) {

        try {
            TrustpilotDataExtractor.extractReviews(TrustpilotFetcher.fetchReviews()).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}