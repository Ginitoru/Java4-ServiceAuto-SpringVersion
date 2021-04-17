package com.gini.iordache.dao.impl.order;


import com.gini.iordache.dao.iterfaces.LaborOrderDao;
import com.gini.iordache.entity.order.LaborOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class LaborOrderDaoImpl implements LaborOrderDao {

    private final EntityManager entityManager;


    @Override
    public void createLaborServiceOrder(LaborOrder laborServiceOrder){
        entityManager.persist(laborServiceOrder);

    }


    @Override
    public int deleteLaborFromOrder(int id){

        String jpql = "DELETE FROM LaborOrder l WHERE l.id =: id ";

        return entityManager.createQuery(jpql)
                                .setParameter("id", id)
                                .executeUpdate();
    }


    @Override
    public Optional<LaborOrder> findLaborOrderById(int id){

        String jpql = "SELECT l FROM LaborOrder l WHERE l.id =: id ";

        return entityManager.createQuery(jpql, LaborOrder.class)
                                .setParameter("id", id)
                                .getResultStream()
                                .findFirst();
    }







}
