package com.gini.iordache.services.interfaces;

import com.gini.iordache.entity.invoice.Invoice;
import com.gini.iordache.entity.order.ServiceOrder;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

public interface InvoiceService {
    @Transactional
    void saveInvoiceToDatabase(ServiceOrder serviceOrder);


//    @Transactional
//    void getInvoiceFromDataBase(ServiceOrder serviceOrder);

    @Transactional
    void getInvoiceFromDataBase(ServiceOrder serviceOrder, HttpServletResponse response);
}
