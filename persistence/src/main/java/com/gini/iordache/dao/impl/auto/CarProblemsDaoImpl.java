package com.gini.iordache.dao.impl.auto;

import com.gini.iordache.dao.CarProblemsDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class CarProblemsDaoImpl implements CarProblemsDao {


    private final EntityManager entityManager;

    @Override
    public void createCarProblems(String carProblems){
        entityManager.persist(carProblems);
    }


    @Override
    public List<CarProblemsDao> carProblemsList(){

        String jpql = "SELECT c FROM CarProblems c";

        return entityManager.createQuery(jpql, CarProblemsDao.class)
                .getResultList();
    }

}
