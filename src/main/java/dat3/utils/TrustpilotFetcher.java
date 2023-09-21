package dat3.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TrustpilotFetcher {

    private static final String url = "https://dk.trustpilot.com/review/www.apple.com";


    public static Elements fetchReviews() throws IOException {

        Document doc = Jsoup.connect(url).get();

        //Getting the next page button
        Element nextButton = doc.getElementsByAttribute("data-pagination-button-next-link").first();
        Elements reviews = doc.select("div.styles_cardWrapper__LcCPA");

        while (nextButton != null) {

            // Navigate to next page
            if (nextButton != null) {
                String nextPageUrl = nextButton.attr("abs:href");
                if (!nextButton.hasAttr("href")) {
                    break;
                }
                doc = Jsoup.connect(nextPageUrl).get();
                nextButton = doc.getElementsByAttribute("data-pagination-button-next-link").first();
            }

            // Extract data from current page
            reviews.addAll(doc.select("div.styles_cardWrapper__LcCPA"));
        }
        return reviews;
    }
}
