package com.gini.iordache.dao.impl.order;

import com.gini.iordache.dao.iterfaces.PartServiceOrderDao;
import com.gini.iordache.entity.order.PartServiceOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.MANDATORY)
public class PartServiceOrderDaoImpl implements PartServiceOrderDao {


    private final EntityManager entityManager;


    @Override
    public void createPartServiceOrder(PartServiceOrder partServiceOrder){
        entityManager.persist(partServiceOrder);

    }






}
