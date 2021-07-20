package com.gini.iordache.services.impl.invoice;

import com.gini.errors.invoice.InvoiceException;
import com.gini.iordache.dao.iterfaces.InvoiceDao;
import com.gini.iordache.entity.invoice.Invoice;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceDao invoiceDao;


    @Override
    @Transactional     //method1
    public void saveInvoiceToDatabase(ServiceOrder serviceOrder){

        Invoice invoice = new Invoice();

        String path = "./web/src/main/resources/invoices/invoice_" + serviceOrder.getId() + ".pdf";

        byte [] pdfToByte = this.convertPDFtoByteArray(path); // ia padf-ul de invoice din aceasta locatie

        var invoiceNumber = "invoice_" + serviceOrder.getId() + ".pdf";

        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoice(pdfToByte);
        invoice.setServiceOrder(serviceOrder);


        invoiceDao.saveInvoiceToDatabase(invoice); //baga invoice pdf in baza de date

        this.deleteInvoiceFromApp(path);           //sterge invoice pdf din locatie
    }



                    //method2
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


           //method3
    private void deleteInvoiceFromApp(String path){
        Path deletePath = Paths.get(path);
        try {
            Files.deleteIfExists(deletePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @Override  ---------> deprecated
//    @Transactional
//    public void getInvoiceFromDataBase(ServiceOrder serviceOrder){
//
//        String path = "./web/src/main/resources/invoices/invoice_" + serviceOrder.getId() + ".pdf";
//
//        Invoice invoice = invoiceDao.findInvoiceByServiceOrder(serviceOrder)
//                                        .orElseThrow(() -> new InvoiceException("Invoice not found"));
//
//        byte [] pdfBytes = invoice.getInvoice();
//
//        try(FileOutputStream fos = new FileOutputStream(path)) {
//
//
//            fos.write(pdfBytes);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    @Transactional
    public void getInvoiceFromDataBase(ServiceOrder serviceOrder, HttpServletResponse response){


        Invoice invoice = invoiceDao.findInvoiceByServiceOrder(serviceOrder)
                                    .orElseThrow(() -> new InvoiceException("Invoice not found"));

        byte [] pdfBytes = invoice.getInvoice();



        response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
        response.setHeader("Content-Disposition", "attachment; filename=" + " invoice"+ serviceOrder.getId() + ".pdf");
        response.setContentLength(pdfBytes.length);


        try(OutputStream os = response.getOutputStream()) {

            os.write(pdfBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
