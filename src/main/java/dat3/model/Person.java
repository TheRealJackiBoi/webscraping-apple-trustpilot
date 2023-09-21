package dat3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_of_reviews")
    private int numberOfReviewsOfRegistrant;

    @Column(name = "country")
    private String countryOfRegistrant;

    @ToString.Exclude
    @OneToMany(mappedBy = "person")
    Set<Review> reviews = new HashSet<>();


    public Person(String name, int numberOfReviewsOfRegistrant, String countryOfRegistrant) {
        this.name = name;
        this.numberOfReviewsOfRegistrant = numberOfReviewsOfRegistrant;
        this.countryOfRegistrant = countryOfRegistrant;
    }

    public void addReview(Review review) {
        review.setPerson(this);
        reviews.add(review);
    }
}
