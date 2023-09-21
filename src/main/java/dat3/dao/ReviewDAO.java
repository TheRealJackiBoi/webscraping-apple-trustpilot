package dat3.dao;

import dat3.model.Person;
import dat3.model.Review;
import jakarta.persistence.EntityManagerFactory;

public class ReviewDAO {

    private static EntityManagerFactory emf;

    private static ReviewDAO instance;

    public static ReviewDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ReviewDAO();
        }
        return instance;
    }

    public void persistReview(Review review) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(review);
            em.getTransaction().commit();
        }
    }

    public Review findReviewById(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Review foundReview = em.find(Review.class, id);
            em.getTransaction().commit();
            return foundReview;
        }
    }
}
