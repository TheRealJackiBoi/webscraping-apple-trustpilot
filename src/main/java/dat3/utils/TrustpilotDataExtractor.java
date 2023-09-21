package dat3.utils;

import dat3.dao.PersonDAO;
import dat3.config.HibernateConfig;
import dat3.dao.ReviewDAO;
import dat3.model.Person;
import dat3.model.Review;
import jakarta.persistence.EntityManagerFactory;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TrustpilotDataExtractor {


    public static List<Review> extractReviews(Elements elements) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("trustpilot");

        List<Review> reviews = elements.stream()
                .map(element -> {

                    //Person
                    Person person = new Person(
                            //Name
                            element.getElementsByClass("typography_heading-xxs__QKBS8 typography_appearance-default__AAY17").first().text(),
                            //Number of reviews
                            Integer.parseInt(element.getElementsByAttribute("data-consumer-reviews-count").attr("data-consumer-reviews-count")),
                            //Country
                            element.getElementsByAttribute("data-consumer-country-typography").text()
                    );
                    //Review
                    Review review = new Review(
                            //Title
                            element.getElementsByAttribute("data-service-review-title-typography").first().text(),
                            //Rating
                            Integer.parseInt(element.getElementsByAttribute("data-service-review-rating").attr("data-service-review-rating")),
                            //Content
                            element.getElementsByAttribute("data-service-review-text-typography").text(),
                            //Date
                            //Parsing the date to using the DateTimeFormatter
                            LocalDateTime.parse((
                                                    //Fetching the date
                                                    element.getElementsByAttribute("data-service-review-date-time-ago").first().attr("datetime")),
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                                    )
                                    //Converting the datetime to date
                                    .toLocalDate()
                    );

                    //Adding the review to the person
                    person.addReview(review);
                    PersonDAO.getInstance(emf).persistPerson(person);
                    ReviewDAO.getInstance(emf).persistReview(review);
                    return review;
                }).toList();

        return reviews;
    }
}
