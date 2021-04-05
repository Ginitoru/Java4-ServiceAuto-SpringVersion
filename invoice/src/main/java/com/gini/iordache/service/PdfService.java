package com.gini.iordache.service;



import com.gini.iordache.entity.clients.Client;
import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.entity.order.ServiceOrder;
import com.lowagie.text.DocumentException;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@AllArgsConstructor
@Service
public class PdfService {


    private final TemplateEngine templateEngine;



    public void createPDF(ServiceOrder serviceOrder){

        var path = "./web/src/main/resources/invoices/invoice_" + serviceOrder.getId() + ".pdf";

        var totalPrice = serviceOrder.getTotalPrice();
        var totalPriceVAT = serviceOrder.getTotalPriceVAT();


        Context context = new Context();

        Client client = serviceOrder.getClient();

        if(client instanceof Person){
            Person person = (Person) serviceOrder.getClient();
            context.setVariable("person", person);
        }

        if(client instanceof Company){
            Company company = (Company) serviceOrder.getClient();
            context.setVariable("company", company);
        }

        context.setVariable("order", serviceOrder);
        context.setVariable("total", totalPrice);
        context.setVariable("totalVAT", totalPriceVAT);
        String processHTML = templateEngine.process("/invoice/invoice", context);


        try (FileOutputStream outputStream = new FileOutputStream(path)){


            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processHTML);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();


        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }


    }



}
