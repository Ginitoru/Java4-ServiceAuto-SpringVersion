package com.gini.iordache.dao.impl.labor;

import com.gini.iordache.dao.iterfaces.LaborDao;
import com.gini.iordache.entity.labor.Labor;
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
public class LaborDaoImpl implements LaborDao {


    private final EntityManager entityManager;

    @Override
    public void createLabor(Labor labor){
        entityManager.persist(labor);
    }


    @Override
    public List<Labor> findLaborByName(String laborDescription){

        String jpql = "SELECT l FROM Labor l WHERE l.laborDescription  LIKE :laborDescription";

        return entityManager.createQuery(jpql, Labor.class)
                                .setParameter("laborDescription", "%"   +laborDescription + "%" )
                                .getResultList();

    }


    @Override
    public int updateLaborTimeAndDescription(double timedLabor, String laborDescription, int id){

        String jpql = "UPDATE Labor l SET l.timedLabor =: timedLabor, l.laborDescription =:laborDescription WHERE l.id =: id" ;


        return entityManager.createQuery(jpql)
                                .setParameter("timedLabor", timedLabor)
                                .setParameter("laborDescription", laborDescription)
                                .setParameter("id", id)
                                .executeUpdate();

    }

    @Override
    public List<Labor> findAllLabors(){

        String jpql = "SELECT l FROM Labor l";

        return entityManager.createQuery(jpql, Labor.class)
                                .getResultList();

    }


    @Override
    public Optional<Labor> findLaborById(int id){

        String jpql = "SELECT l FROM Labor l WHERE l.id =: id";

        return entityManager.createQuery(jpql, Labor.class)
                                .setParameter("id", id)
                                .getResultStream()
                                .findFirst();
    }


    @Override
    public int deleteLabor(int id){

        String jpql = "DELETE FROM Labor l WHERE l.id =: id";

        return entityManager.createQuery(jpql)
                                .setParameter("id", id)
                                .executeUpdate();

    }
}
