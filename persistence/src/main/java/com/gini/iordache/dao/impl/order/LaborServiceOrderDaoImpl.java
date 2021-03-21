package com.gini.iordache.dao.impl.order;


import com.gini.iordache.dao.iterfaces.LaborServiceOrderDao;
import com.gini.iordache.entity.order.LaborServiceOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class LaborServiceOrderDaoImpl implements LaborServiceOrderDao {

    private final EntityManager entityManager;


    @Override
    public void createLaborServiceOrder(LaborServiceOrder laborServiceOrder){
        entityManager.persist(laborServiceOrder);

    }


}
