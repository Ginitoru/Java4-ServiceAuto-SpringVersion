package com.gini.iordache.dao.impl.auto;

import com.gini.iordache.dao.iterfaces.CarProblemsDao;
import com.gini.iordache.entity.order.CarProblems;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor

@Repository
public class CarProblemsDaoImpl implements CarProblemsDao {


    private final EntityManager entityManager;

    @Override
    @Transactional
    public void createCarProblems(CarProblems carProblems){
        entityManager.persist(carProblems);
    }


    @Override
    public List<CarProblemsDao> carProblemsList(){

        String jpql = "SELECT c FROM CarProblems c";

        return entityManager.createQuery(jpql, CarProblemsDao.class)
                            .getResultList();
    }




}
