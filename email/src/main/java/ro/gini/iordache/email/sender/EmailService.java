package ro.gini.iordache.email.sender;


import com.gini.iordache.entity.user.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.ReportAsSingleViolation;
import java.net.UnknownHostException;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{


    private final JavaMailSenderImpl mailSender;



    @Override
    public void sendEmail(User user){

        var userEmail = user.getEmail();
        var username = user.getUsername();
        var token = user.getActivationToken().getToken();




        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, "utf-8");

        String link = "http://localhost:8080/activate?token=" + token + "&email=" + userEmail;
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
                        "<a href=\"" + link +"\"><button type=\"button\">Activate account</button></a>\n";





        try {
            helper.setSubject("Confirmation Token");
            helper.setTo(userEmail);
            helper.setFrom("application@baubau.com");
            helper.setText(email, true);
            mailSender.send(mailMessage);


        } catch ( MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send mail :(");
        }

    }
}
