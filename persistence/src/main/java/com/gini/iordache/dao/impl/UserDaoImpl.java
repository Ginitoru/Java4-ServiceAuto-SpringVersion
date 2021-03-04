package com.gini.iordache.dao.impl;

import com.gini.iordache.dao.UserDao;
import com.gini.iordache.entity.ActivationToken;
import com.gini.iordache.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
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

        //desi Set<String> authorities sunt fetch=eager nu le incarca in Privider cand setam autentificarea
        //ca sa nu mai pun @Transactional in clasele de AuthenticationProvider am facut un JOIN FETCH
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

    @Override
    public int activateUserAccount(User user){

   //     //todo: nu pre pare ok, dar pt inceput merge-> de vazut daca pot sa o mai optimizez

        String jpql = "UPDATE ActivationToken a SET a.activatedAt =: activatedAt WHERE a.id =: id ";

        String jpql2 = "UPDATE User u SET u.isEnabled = 1, u.isNonLoked = 1 WHERE u.username =: username";

        entityManager.createQuery(jpql)
                    .setParameter("id", user.getActivationToken().getId())
                    .setParameter("activatedAt", LocalDateTime.now())
                    .executeUpdate();



        return entityManager.createQuery(jpql2)
                        .setParameter("username", user.getUsername())
                        .executeUpdate();

    }
}
