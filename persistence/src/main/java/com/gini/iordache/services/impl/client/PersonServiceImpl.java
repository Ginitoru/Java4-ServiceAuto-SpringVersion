package com.gini.iordache.services.impl.client;

import com.gini.iordache.dao.PersonDao;
import com.gini.iordache.entity.clients.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements com.gini.iordache.services.PersonService {

    private final PersonDao personDao;

    @Override
    @Transactional
    public void createPerson(Person person){

        Optional<Person> optPerson = personDao.findPersonByCnp(person.getCnp());

        if(optPerson.isEmpty()){
            personDao.createPerson(person);
            return;
        }

        throw new RuntimeException("Person already exists");
    }
}
