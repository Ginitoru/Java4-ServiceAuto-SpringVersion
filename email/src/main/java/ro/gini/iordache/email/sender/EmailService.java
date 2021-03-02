package ro.gini.iordache.email.sender;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import ro.gini.iordache.email.config.EmailConfig;

@Component
public class EmailSender {


    private final EmailConfig emailConfig;


    @Autowired
    public EmailSender(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }




    public EmailSender() {
    }

    public void sedEmail(String email){

    }


    private void setMailConfig(){

    }








}
