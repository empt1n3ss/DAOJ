package org.example.repository;

import org.example.entity.Person;
import org.example.entity.PersonId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomizedPersonsCrudRepository extends CrudRepository<Person, PersonId> {
    List<Person> findByCityOfLiving(String city);
    List<Person> findByPersonIdAgeLessThanOrderByPersonIdAgeAsc(int age);
    Optional<Person> findByPersonIdNameAndPersonIdSurname(String name, String surname);

    @Query("select p from Person p where p.cityOfLiving = :city")
    List<Person> findByCityOfLivingQ(@Param("city") String city);
    @Query("select p from Person p where p.personId.age < :age order by p.personId.age ASC")
    List<Person> findByPersonIdAgeLessThanOrderByPersonIdAgeAscQ(@Param("age") int age);
    @Query("select p from Person p where p.personId.name = :name and p.personId.surname = :surname")
    Optional<Person> findByPersonIdNameAndPersonIdSurnameQ(@Param("name, surname")String name, String surname);
}
