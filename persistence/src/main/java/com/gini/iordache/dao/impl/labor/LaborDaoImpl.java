package com.gini.iordache.dao.impl.labor;

import com.gini.iordache.entity.labor.Labor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class LaborDaoImpl implements com.gini.iordache.dao.LaborDao {


    private final EntityManager entityManager;

    @Override
    public void createLabor(Labor labor){
        entityManager.persist(labor);
    }


    @Override
    public List<Labor> findLaborByName(String laborDescription){

        String jpql = "SELECT l FROM Labor l WHERE l.laborDescription =:laborDescription";

        return entityManager.createQuery(jpql, Labor.class)
                                .getResultList();

    }
}
