package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.invoice.Invoice;
import com.gini.iordache.entity.order.ServiceOrder;

import java.util.Optional;

public interface InvoiceDao {

    void saveInvoiceToDatabase(Invoice invoice);

    Optional<Invoice> findInvoiceByServiceOrder(ServiceOrder serviceOrder);
}
