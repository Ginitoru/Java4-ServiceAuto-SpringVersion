package com.gini.iordache.services.impl.order;


import com.gini.iordache.dao.iterfaces.LaborServiceOrderDao;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.services.interfaces.LaborServiceOrderService;
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
