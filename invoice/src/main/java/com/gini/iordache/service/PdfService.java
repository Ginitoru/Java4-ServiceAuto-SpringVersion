package com.gini.iordache.service;



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



    public void createPDF(double totalPrice, double totalPriceWithVAT, ServiceOrder serviceOrder){

        Context context = new Context();

        context.setVariable("order", serviceOrder);
        context.setVariable("total", totalPrice);
        context.setVariable("totalVAT", serviceOrder);
        String processHTML = templateEngine.process("/invoice/invoice", context);

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("invoice.pdf");

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processHTML);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
            outputStream.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }


    }



}
