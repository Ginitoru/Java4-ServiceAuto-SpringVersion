package com.gini.iordache.dao.impl.order;

import com.gini.iordache.dao.iterfaces.PartServiceOrderDao;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(propagation = Propagation.MANDATORY)
public class PartServiceOrderDaoImpl implements PartServiceOrderDao {


    private final EntityManager entityManager;


    @Override
    public void createPartServiceOrder(PartServiceOrder partServiceOrder){
        entityManager.persist(partServiceOrder);

    }



    @Override
    public Optional<PartServiceOrder> findPartOrderByPartName(String partNumber, ServiceOrder serviceOrder){

        String jpql = "SELECT p FROM PartServiceOrder p WHERE p.partNumber =: partNumber AND p.serviceOrder =: serviceOrder";

        return entityManager.createQuery(jpql, PartServiceOrder.class)
                                .setParameter("partNumber", partNumber)
                                .setParameter("serviceOrder", serviceOrder)
                                .getResultStream()
                                .findFirst();
    }



    @Override
    public int deletePartFromServiceOrder(String partNumber){

        String jpql = "DELETE FROM PartServiceOrder p WHERE p.partNumber =: partNumber";

        return entityManager.createQuery(jpql)
                                .setParameter("partNumber", partNumber)
                                .executeUpdate();

    }


    @Override
    public int updatePartOrderCount(int id, int increment){

        String jpql = "UPDATE PartServiceOrder p SET p.count = p.count + :increment WHERE p.id =: id";

        return entityManager.createQuery(jpql)
                                .setParameter("increment", increment)
                                .setParameter("id", id)
                                .executeUpdate();

    }






}
