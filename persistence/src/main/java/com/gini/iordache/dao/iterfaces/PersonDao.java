package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.clients.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {
    void createPerson(Person person);

    Optional<Person> findPersonByCnp(String cnp);

    //todo: de vazut daca merge jpql-ul -> nu sunt sigur 100%
    List<Person> findPersonByFirstNameOrLastName(String firstOrLastName);
}
