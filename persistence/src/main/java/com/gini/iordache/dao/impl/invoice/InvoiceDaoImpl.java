package com.gini.iordache.dao.impl.invoice;

import com.gini.iordache.dao.iterfaces.InvoiceDao;
import com.gini.iordache.entity.invoice.Invoice;
import com.gini.iordache.entity.order.ServiceOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;


@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class InvoiceDaoImpl implements InvoiceDao {



    private final EntityManager entityManager;


    @Override
    public void saveInvoiceToDatabase(Invoice invoice){
        entityManager.persist(invoice);
    }


    @Override
    public Optional<Invoice> findInvoiceByServiceOrder(ServiceOrder serviceOrder){

        String jpql = "SELECT i FROM Invoice i WHERE i.serviceOrder =: serviceOrder";


        return entityManager.createQuery(jpql, Invoice.class)
                                .setParameter("serviceOrder", serviceOrder)
                                .getResultStream()
                                .findFirst();
    }
}
