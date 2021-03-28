package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.invoice.Invoice;

public interface InvoiceDao {

    void saveInvoiceToDatabase(Invoice invoice);
}
