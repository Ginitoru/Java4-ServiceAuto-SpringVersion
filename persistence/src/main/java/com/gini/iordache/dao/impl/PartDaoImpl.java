package com.gini.iordache.dao.impl;

import com.gini.iordache.dao.PartDao;
import com.gini.iordache.entity.auto.Part;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class PartDaoImpl implements PartDao {

    private final EntityManager entityManager;

    @Override
    public void createPart(Part part) {
        entityManager.persist(part);
    }

    @Override
    public Optional<Part> findPartByName(String partName){

        String jpql = "SELECT p FROM Part p WHERE p.partName =: partName";

        return entityManager.createQuery(jpql, Part.class)
                                .setParameter("partName", partName)
                                .getResultStream()
                                .findFirst();

    }

    @Override
    public Optional<Part> findPartByPartNumber(String partNumber){

        String jpql = "SELECT p FROM Part p WHERE p.partName =: partName";

        return entityManager.createQuery(jpql, Part.class)
                                .setParameter("partName", partNumber)
                                .getResultStream()
                                .findFirst();

    }


    @Override
    public int updatePartPrice(double price, String partNumber){

        String jpql = "UPDATE Part p SET p.price =: price WHERE p.partNumber =: partNumber";

        return entityManager.createQuery(jpql)
                                .setParameter("price", price)
                                .setParameter("partNumber", partNumber)
                                .executeUpdate();


    }


}
