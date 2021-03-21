package com.gini.iordache.dao.impl.client;

import com.gini.iordache.dao.iterfaces.PersonDao;
import com.gini.iordache.entity.clients.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class PersonDaoImpl implements PersonDao {


    private final EntityManager entityManager;


    @Override
    public void createPerson(Person person){
        entityManager.persist(person);
    }


    @Override
    public Optional<Person> findPersonByCnp(String cnp){

        String jpql = "SELECT p FROM Person p WHERE p.cnp =: cnp";

        return entityManager.createQuery(jpql, Person.class)
                                .setParameter("cnp", cnp)
                                .getResultStream()
                                .findFirst();

    }


    //todo: de vazut daca merge jpql-ul -> nu sunt sigur 100%
    @Override
    public List<Person> findPersonByFirstNameOrLastName(String firstOrLastName){

        String jpql = "SELECT p FROM Person p WHERE p.firstName =: firstOrLastName OR p.lastName =: firstOrLastName ";

         return entityManager.createQuery(jpql, Person.class)
                                .getResultList();

    }


}
