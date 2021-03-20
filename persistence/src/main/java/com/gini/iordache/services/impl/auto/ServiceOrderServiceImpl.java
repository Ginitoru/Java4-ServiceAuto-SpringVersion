package com.gini.iordache.services.impl.auto;

import com.gini.iordache.dao.ServiceOrderDao;
import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.auto.ServiceOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Service
public class ServiceOrderServiceImpl implements com.gini.iordache.services.ServiceOrderService {


    private final ServiceOrderDao serviceOrderDao;



    @Override
    @Transactional
    public List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus(){
        return serviceOrderDao.allServiceOrderIdAndStatus();
    }



    @Override
    @Transactional
    public void createServiceOrder(ServiceOrder serviceOrder){
        serviceOrderDao.createServiceOrder(serviceOrder);
    }


    @Override
    @Transactional
    public Set<ServiceOrder> findAllServiceOrder(){
        return serviceOrderDao.findAllServiceOrders();
    }




}
