package com.gini.iordache.dao.impl.order;

import com.gini.iordache.dao.iterfaces.ServiceOrderDao;
import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
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




}
