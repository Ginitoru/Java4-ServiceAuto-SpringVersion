package com.gini.iordache.dao.impl.order;


import com.gini.iordache.dao.iterfaces.LaborServiceOrderDao;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class LaborServiceOrderDaoImpl implements LaborServiceOrderDao {

    private final EntityManager entityManager;


    @Override
    public void createLaborServiceOrder(LaborServiceOrder laborServiceOrder){
        entityManager.persist(laborServiceOrder);

    }


    @Override
    public int deleteLaborFromOrder(int id){

        String jpql = "DELETE FROM LaborServiceOrder l WHERE l.id =: id ";

        return entityManager.createQuery(jpql)
                                .setParameter("id", id)
                                .executeUpdate();
    }


    @Override
    public Optional<LaborServiceOrder> findLaborOrderById(int id){

        String jpql = "SELECT l FROM LaborServiceOrder l WHERE l.id =: id ";

        return entityManager.createQuery(jpql, LaborServiceOrder.class)
                                .setParameter("id", id)
                                .getResultStream()
                                .findFirst();
    }







}
