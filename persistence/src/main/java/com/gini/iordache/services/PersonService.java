package com.gini.iordache.services;

import com.gini.iordache.entity.clients.Person;
import org.springframework.transaction.annotation.Transactional;

public interface PersonService {
    @Transactional
    void createPerson(Person person);
}