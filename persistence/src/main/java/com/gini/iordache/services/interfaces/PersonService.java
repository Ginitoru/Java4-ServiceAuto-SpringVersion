package com.gini.iordache.services.interfaces;

import com.gini.iordache.entity.clients.Person;
import org.springframework.transaction.annotation.Transactional;

public interface PersonService {
    @Transactional
    void createPerson(Person person);

    @Transactional
    Person findPersonByCnp(String cnp);
}
