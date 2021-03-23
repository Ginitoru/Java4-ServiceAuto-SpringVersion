package com.gini.iordache.dao.iterfaces;


import com.gini.iordache.entity.order.LaborServiceOrder;

import java.util.Optional;

public interface LaborServiceOrderDao {
    void createLaborServiceOrder(LaborServiceOrder laborServiceOrder);

    int deleteLaborFromOrder(int id);

    Optional<LaborServiceOrder> findLaborOrderById(int id);
}
