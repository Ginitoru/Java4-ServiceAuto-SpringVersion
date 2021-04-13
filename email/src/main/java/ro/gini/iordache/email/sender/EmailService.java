package ro.gini.iordache.email.sender;


import com.gini.iordache.entity.user.User;
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
    private final EmailHtmlRendererImpl emailHtmlRenderer;



    @Override
    public void sendEmail(User user){

        var userEmail = user.getEmail();
        var username = user.getUsername();
        var token = user.getActivationToken().getToken();



        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, "utf-8");

        String link = "http://localhost:8080/activate?token=" + token + "&email=" + userEmail;
        String email2 = emailHtmlRenderer.constructHtmlMailPage(link, username);


        try {
            helper.setSubject("Confirmation Token");
            helper.setTo(userEmail);
            helper.setFrom("application@baubau.com");
            helper.setText(email2, true);
            mailSender.send(mailMessage);


        } catch ( MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send mail :(");
        }

    }
}
