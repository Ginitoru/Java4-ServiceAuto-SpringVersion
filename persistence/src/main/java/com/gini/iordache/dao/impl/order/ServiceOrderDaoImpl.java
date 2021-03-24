package com.gini.iordache.dao.impl.order;

import com.gini.iordache.dao.iterfaces.ServiceOrderDao;
import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.utility.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class ServiceOrderDaoImpl implements ServiceOrderDao {


    private final EntityManager entityManager;

    @Override
    public void createServiceOrder(ServiceOrder serviceOrder){
        entityManager.persist(serviceOrder);
    }


    @Override
    public Set<ServiceOrder> findAllServiceOrders(){

        String jpql = "SELECT s FROM ServiceOrder s";

        return entityManager.createQuery(jpql, ServiceOrder.class)
                        .getResultStream()
                        .collect(Collectors.toSet());
    }


    @Override
    public List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus(){

        String jpql ="SELECT  NEW  com.gini.iordache.dto.ServiceOrderIdAndStatusDto(s.id, s.orderStatus) FROM ServiceOrder s";

        return entityManager.createQuery(jpql, ServiceOrderIdAndStatusDto.class)
                        .getResultList();
    }


    @Override
    public Optional<ServiceOrder> findServiceOrderById(int id){

        String jpql = "SELECT s FROM ServiceOrder s WHERE s.id =: id";

        return entityManager.createQuery(jpql, ServiceOrder.class)
                                .setParameter("id", id)
                                .getResultStream()
                                .findFirst();

    }

    @Override
    public ServiceOrder updateServiceOrder(ServiceOrder serviceOrder){
        return entityManager
                        .merge(serviceOrder);
    }


    @Override
    public ServiceOrder findServiceOrderParts(int id){

        String jpql = "SELECT s FROM ServiceOrder s LEFT JOIN FETCH s.parts WHERE s.id =: id";


        return entityManager.createQuery(jpql, ServiceOrder.class)
                        .setParameter("id", id)
                        .getSingleResult();

    }


    @Override
    public List<PartServiceOrder> getPartsFormServiceOrder(int id){
        String jpql ="SELECT parts FROM ServiceOrder s JOIN s.parts parts WHERE s.id =: id";

        return entityManager.createQuery(jpql, PartServiceOrder.class)
                .setParameter("id", id)
                .getResultList();

    }


    @Override
    public List<LaborServiceOrder> findAllLaborsInOrder(int id){

        String jpql = "SELECT labors FROM ServiceOrder s JOIN s.labors labors WHERE s.id =: id";


        return entityManager.createQuery(jpql, LaborServiceOrder.class)
                .setParameter("id", id)
                .getResultList();

    }

   //-> https://stackoverflow.com/questions/30088649/how-to-use-multiple-join-fetch-in-one-jpql-query
    @Override
    public ServiceOrder findCompleteServiceOrderById(int id){

        String jpql1 = "SELECT s FROM ServiceOrder s LEFT JOIN FETCH s.parts WHERE s.id = :id";
        String jpql2 = "SELECT s FROM ServiceOrder s LEFT JOIN FETCh s.labors l WHERE s IN :serviceOrder";


        ServiceOrder serviceOrder = entityManager.createQuery(jpql1, ServiceOrder.class)
                                                    .setParameter("id", id)
                                                    .getSingleResult();


        serviceOrder = entityManager.createQuery(jpql2, ServiceOrder.class)
                                                    .setParameter("serviceOrder", serviceOrder)
                                                    .getSingleResult();

        return serviceOrder;
    }


    @Override
    public int updateOrderStatus(OrderStatus  orderStatus, int id){

        String jpql = "UPDATE ServiceOrder s SET s.orderStatus =: orderStatus WHERE s.id =: id";


        return entityManager.createQuery(jpql)
                                .setParameter("orderStatus", orderStatus)
                                .setParameter("id", id)
                                .executeUpdate();

    }





}
