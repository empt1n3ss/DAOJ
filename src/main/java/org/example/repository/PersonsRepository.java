package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonsRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public void save(Person person){
        entityManager.persist(person);
    }
    public void delete(Person person){
        entityManager.remove(person);
    }
    public List<Person> getPersonByCity(String city){
        TypedQuery<Person> query = entityManager.createQuery(
                "Select p FROM Person p WHERE p.city = :city", Person.class
        );
        query.setParameter("city", city);
        return query.getResultList();
    }
}
