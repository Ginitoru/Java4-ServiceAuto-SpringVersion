package com.gini.iordache.services.impl.order;

import com.gini.iordache.dao.iterfaces.PartDao;
import com.gini.iordache.dao.iterfaces.ServiceOrderDao;
import com.gini.iordache.dto.PartDto;
import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {


    private final ServiceOrderDao serviceOrderDao;
    private final PartDao partDao;



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


    @Override
    @Transactional
    public ServiceOrder findServiceOrderById(int id){

        return serviceOrderDao.findServiceOrderById(id)
                              .orElseThrow(() -> new RuntimeException("Service order not found"));

    }


    @Override
    @Transactional
    public ServiceOrder updateServiceOrder(ServiceOrder serviceOrder, int decrement, String partNumber){

        partDao.decreasePartCount(decrement, partNumber);
        return serviceOrderDao.updateServiceOrder(serviceOrder);
    }


    @Override
    @Transactional
    public ServiceOrder findServiceOrderParts(int id){
        return serviceOrderDao
                            .findServiceOrderParts(id);
    }



    @Override
    @Transactional
    public List<PartServiceOrder> getPartsFormServiceOrder(int id){
        return serviceOrderDao.getPartsFormServiceOrder(id);
    }




}
