package com.gini.iordache.services.impl.order;


import com.gini.iordache.dao.LaborServiceOrderDao;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.services.LaborServiceOrderService;
import com.gini.iordache.services.PartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class LaborServiceOrderServiceImpl implements LaborServiceOrderService {

    private final LaborServiceOrderDao laborServiceOrderDao;



    @Override
    @Transactional
    public void createLaborServiceOrder(LaborServiceOrder laborServiceOrder){
        laborServiceOrderDao.createLaborServiceOrder(laborServiceOrder);
    }

}
