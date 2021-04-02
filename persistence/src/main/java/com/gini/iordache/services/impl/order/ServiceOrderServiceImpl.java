package com.gini.iordache.services.impl.order;

import com.gini.errors.order.ClientNotSelectedException;
import com.gini.errors.order.VehicleNotSelectedException;
import com.gini.iordache.dao.iterfaces.PartDao;
import com.gini.iordache.dao.iterfaces.ServiceOrderDao;

import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import com.gini.iordache.utility.OrderStatus;
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

        var vehicleId = serviceOrder.getVehicle().getId();
        var clientId = serviceOrder.getClient().getId();


        if(vehicleId == 0){
            throw new VehicleNotSelectedException("Vehicle not selected at order creation");
        }


        if(clientId == 0){
            throw new ClientNotSelectedException("Client not selected at order creation!");
        }


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


    @Override
    @Transactional
    public List<LaborServiceOrder> findAllLaborsInOrder(int id){

        return serviceOrderDao.findAllLaborsInOrder(id);

    }


    @Override
    @Transactional
    public ServiceOrder findCompleteServiceOrderById(int id){
        return serviceOrderDao.findCompleteServiceOrderById(id);
    }



    @Override
    @Transactional
    public int updateOrderStatus(OrderStatus orderStatus, int id){
        return serviceOrderDao.updateOrderStatus(orderStatus, id);
    }






}
