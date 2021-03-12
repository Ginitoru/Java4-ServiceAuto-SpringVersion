package com.gini.iordache.dao.impl.auto;

import com.gini.iordache.entity.auto.ServiceOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Set;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class ServiceOrderDaoImpl implements ServiceOrder {


    private final EntityManager entityManager;

    @Override
    public void createServiceOrder(ServiceOrder serviceOrder){
        entityManager.persist(serviceOrder);
    }


    @Override
    public Set<ServiceOrder> findAllServiceOrders(){

        String jpql = "SELECT s FROM ServiceOrder s";

        entityManager.createQuery(jpql, ServiceOrder.class)
                        .getResultList();
    }




}
