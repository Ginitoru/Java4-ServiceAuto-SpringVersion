package com.gini.iordache.services.impl.client;

import com.gini.errors.client.PersonAlreadyExistsException;
import com.gini.errors.client.PersonNotFoundException;
import com.gini.iordache.dao.iterfaces.PersonDao;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.services.interfaces.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    @Override
    @Transactional
    public void createPerson(Person person){

        Optional<Person> optPerson = personDao.findPersonByCnp(person.getCnp());

        if(optPerson.isEmpty()){
            personDao.createPerson(person);
            return;
        }

        throw new PersonAlreadyExistsException("Person already exists");
    }


    @Override
    @Transactional
    public Person findPersonByCnp(String cnp){

        return personDao.findPersonByCnp(cnp)
                        .orElseThrow(() -> new PersonNotFoundException("Person not found!"));

    }
}
