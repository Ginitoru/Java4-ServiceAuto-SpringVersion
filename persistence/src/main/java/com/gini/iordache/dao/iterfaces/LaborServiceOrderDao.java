package com.gini.iordache.dao.iterfaces;


import com.gini.iordache.entity.order.LaborOrder;

import java.util.Optional;

public interface LaborServiceOrderDao {
    void createLaborServiceOrder(LaborOrder laborServiceOrder);

    int deleteLaborFromOrder(int id);

    Optional<LaborOrder> findLaborOrderById(int id);
}
