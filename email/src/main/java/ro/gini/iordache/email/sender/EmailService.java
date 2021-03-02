package ro.gini.iordache.email.sender;


import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{


    private final JavaMailSenderImpl mailSender;



    @Override
    public void sendEmail(String userEmail, String username, String token) {



        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, "utf-8");

        String link = "http://localhost:8080/";
                //test page
        String email= "<h1>Boss you got mail</h1>" + "\n" +
                        "<hr>" + "\n" +
                        "<br><br>" +  "\n " +
                        "<h2>Holla " + username + " </h2>" +
                        "<h2> Click the button below to activate account</h2>" +
                        "<a href=\"http://localhost:8080\"> Register Page</a>" +
                        "<br>" +
                             "<a href=\"" + link + "\">Activate Now</a>" +
                        "<form action=\"http://localhost:8080\">\n" +
                        "<a href=\"http://localhost:8080\"><button type=\"button\">Activate account</button></a>\n";





        try {
            helper.setSubject("Confirmation Token");
            helper.setTo(userEmail);
            helper.setFrom("application@baubau.com");
            helper.setText(email, true);
            mailSender.send(mailMessage);


        } catch (MessagingException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to send mail :(");
        }

    }
}
