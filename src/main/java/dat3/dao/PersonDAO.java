package dat3.dao;

import dat3.model.Person;
import jakarta.persistence.EntityManagerFactory;

public class PersonDAO {
    private static EntityManagerFactory emf;

    private static PersonDAO instance;

    public static PersonDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonDAO();
        }
        return instance;
    }

    public void persistPerson(Person person) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }
    }

    public Person findPersonByName(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person foundPerson = em.find(Person.class, id);
            em.getTransaction().commit();
            return foundPerson;
        }
    }

}
