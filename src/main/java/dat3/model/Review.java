package dat3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Registrant
    @ManyToOne
    private Person person;

    //Review info
    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private double rating;

    @Column(name = "review", columnDefinition = "TEXT")
    private String content;

    @Column(name = "date")
    private LocalDate date;

    public Review(String title, double rating, String content, LocalDate date) {
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.date = date;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
