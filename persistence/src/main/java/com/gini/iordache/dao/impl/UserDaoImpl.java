package com.gini.iordache.dao.impl;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }


    @Override
    public Optional<User> findUserByUsername(String username) {

        String jpql = "SELECT u FROM User u JOIN FETCH u.authorities WHERE u.username =: username";

        return entityManager.createQuery(jpql, User.class)
                            .setParameter("username", username)
                            .getResultStream()
                            .findFirst();
    }


    @Override
    public Optional<User> findUserByEmail(String email) {

        String jpql = "SELECT u FROM User u WHERE u.email =: email";

        return entityManager.createQuery(jpql, User.class)
                            .setParameter("email", email)
                            .getResultStream()
                            .findFirst();

    }
}
