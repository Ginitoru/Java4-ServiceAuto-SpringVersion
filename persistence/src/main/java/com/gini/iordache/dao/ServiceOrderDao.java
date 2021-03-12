package com.gini.iordache.dao;

import com.gini.iordache.entity.auto.ServiceOrder;

import java.util.Set;

public interface ServiceOrderDao {
    void createServiceOrder(com.gini.iordache.entity.auto.ServiceOrder serviceOrder);


    Set<ServiceOrder> findAllServiceOrders();
}
