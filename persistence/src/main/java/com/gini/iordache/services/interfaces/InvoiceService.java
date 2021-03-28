package com.gini.iordache.services.interfaces;

import com.gini.iordache.entity.invoice.Invoice;
import com.gini.iordache.entity.order.ServiceOrder;
import org.springframework.transaction.annotation.Transactional;

public interface InvoiceService {
    @Transactional
    void saveInvoiceToDatabase(ServiceOrder serviceOrder);
}
