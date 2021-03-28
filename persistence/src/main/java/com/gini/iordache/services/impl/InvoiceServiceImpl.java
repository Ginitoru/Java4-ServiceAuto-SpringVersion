package com.gini.iordache.services.impl;

import com.gini.iordache.dao.iterfaces.InvoiceDao;
import com.gini.iordache.entity.invoice.Invoice;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceDao invoiceDao;


    @Override
    @Transactional
    public void saveInvoiceToDatabase(ServiceOrder serviceOrder){

        Invoice invoice = new Invoice();

        String path = "./web/src/main/resources/invoices/invoice_" + serviceOrder.getId() + ".pdf";

        byte [] pdfToByte = convertPDFtoByteArray(path);

        var invoiceNumber = "invoice_" + serviceOrder.getId() + ".pdf";

        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoice(pdfToByte);
        invoice.setServiceOrder(serviceOrder);


        invoiceDao.saveInvoiceToDatabase(invoice);


    }


    private byte[] convertPDFtoByteArray(String path){


        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

        try(InputStream inputStream = new FileInputStream(path); byteOutputStream) {

            byte[] buffer = new byte[1024];
            int bytesRead;


            while((bytesRead = inputStream.read(buffer)) != -1){
                byteOutputStream.write(buffer,0, bytesRead);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


       return byteOutputStream.toByteArray();

    }


}
