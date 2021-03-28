package com.gini.iordache.dao.impl.invoice;

import com.gini.iordache.dao.iterfaces.InvoiceDao;
import com.gini.iordache.entity.invoice.Invoice;
import com.gini.iordache.entity.order.ServiceOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class InvoiceDaoImpl implements InvoiceDao {



    private final EntityManager entityManager;


    @Override
    public void saveInvoiceToDatabase(Invoice invoice){
        entityManager.persist(invoice);
    }





}
