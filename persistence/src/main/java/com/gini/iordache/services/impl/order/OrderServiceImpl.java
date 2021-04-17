package com.gini.iordache.services.impl.order;

import com.gini.errors.order.ClientNotSelectedException;
import com.gini.errors.order.OrderIsClosedException;
import com.gini.errors.order.VehicleNotSelectedException;
import com.gini.iordache.dao.iterfaces.PartDao;
import com.gini.iordache.dao.iterfaces.OrderDao;
import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.PartOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.service.PdfService;
import com.gini.iordache.services.interfaces.InvoiceService;
import com.gini.iordache.services.interfaces.OrderService;
import com.gini.iordache.util.TwoDigitsDouble;
import com.gini.iordache.utility.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {


    private final OrderDao serviceOrderDao;
    private final PartDao partDao;
    private final PdfService pdfService;
    private final InvoiceService invoiceService;




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
    public List<PartOrder> getPartsFormServiceOrder(int id){
        return serviceOrderDao.getPartsFormServiceOrder(id);
    }


    @Override
    @Transactional
    public List<LaborOrder> findAllLaborsInOrder(int id){

        return serviceOrderDao.findAllLaborsInOrder(id);

    }





    @Override
    @Transactional
    public int closeOrder(ServiceOrder serviceOrder){

        var orderStatus = serviceOrder.getOrderStatus();
        var orderId = serviceOrder.getId();

        if(orderStatus.equals(OrderStatus.CLOSE)){
            throw new OrderIsClosedException("Order is already CLOSED");

        }


            pdfService.createPDF(serviceOrder);
            invoiceService.saveInvoiceToDatabase(serviceOrder);

        return serviceOrderDao.updateOrderStatus(OrderStatus.CLOSE, orderId);
    }


    @Override
//    @Cacheable(value = "orderCache", key = "'order' +#id")
    @Transactional //metoda 1
    public ServiceOrder findCompleteServiceOrderById(int id){


        ServiceOrder serviceOrder = serviceOrderDao.findCompleteServiceOrderById(id);


        double partsTotalPrice = this.getPartsTotalPrice(serviceOrder);
        double partsTotalPriceVAT = TwoDigitsDouble.formatPrice(partsTotalPrice * 1.19);


        double laborPrice = this.getLaborTotalPrice(serviceOrder);
        double laborPriceVAT = TwoDigitsDouble.formatPrice(laborPrice * 1.19);


        double totalPrice = TwoDigitsDouble.formatPrice(partsTotalPrice + laborPrice);
        double totalPriceVAT = TwoDigitsDouble.formatPrice(totalPrice  * 1.19);


                            serviceOrder.setPartsTotalPrice(partsTotalPrice);
                            serviceOrder.setPartsTotalPriceVAT(partsTotalPriceVAT);

                            serviceOrder.setLaborTotalPrice(laborPrice);
                            serviceOrder.setLaborTotalPriceVAT(laborPriceVAT);

                            serviceOrder.setTotalPrice(totalPrice);
                            serviceOrder.setTotalPriceVAT(totalPriceVAT);


        return serviceOrder;
    }




    //method 2 intra in 1
    private double getPartsTotalPrice(ServiceOrder serviceOrder){
        return serviceOrder.getParts()
                .stream()
                .mapToDouble(this::partPrice)
                .map(price -> TwoDigitsDouble.formatPrice(price))
                .sum();                               //face suma preturilor la piese

    }


    //method 3 intra in 2
    private double partPrice(PartOrder part){
        return part.getCount() * part.getPrice();
    }


    //method 4 intra in 1
    private double getLaborTotalPrice(ServiceOrder serviceOrder){

        return serviceOrder.getLabors()
                .stream()
                .mapToDouble( l -> l.getLaborPrice())
                .map(price -> TwoDigitsDouble.formatPrice(price))
                .sum();

    }

}
