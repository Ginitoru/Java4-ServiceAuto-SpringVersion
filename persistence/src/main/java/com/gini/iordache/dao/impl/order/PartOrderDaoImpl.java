package com.gini.iordache.dao.impl.order;

import com.gini.iordache.dao.iterfaces.PartOrderDao;
import com.gini.iordache.entity.order.PartOrder;
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
public class PartOrderDaoImpl implements PartOrderDao {


    private final EntityManager entityManager;


    @Override
    public void createPartOrder(PartOrder partServiceOrder){
        entityManager.persist(partServiceOrder);

    }



    @Override
    public Optional<PartOrder> findPartOrderByPartName(String partNumber, ServiceOrder serviceOrder){

        String jpql = "SELECT p FROM PartOrder p WHERE p.partNumber =: partNumber AND p.serviceOrder =: serviceOrder";

        return entityManager.createQuery(jpql, PartOrder.class)
                                .setParameter("partNumber", partNumber)
                                .setParameter("serviceOrder", serviceOrder)
                                .getResultStream()
                                .findFirst();
    }



    @Override
    public int deletePartFromServiceOrder(String partNumber){

        String jpql = "DELETE FROM PartOrder p WHERE p.partNumber =: partNumber";

        return entityManager.createQuery(jpql)
                                .setParameter("partNumber", partNumber)
                                .executeUpdate();

    }


    @Override
    public int updatePartOrderCount(int id, int increment){

        String jpql = "UPDATE PartOrder p SET p.count = p.count + :increment WHERE p.id =: id";

        return entityManager.createQuery(jpql)
                                .setParameter("increment", increment)
                                .setParameter("id", id)
                                .executeUpdate();

    }


}
