package ro.gini.iordache.email.sender;

import com.gini.iordache.entity.user.User;

import javax.mail.MessagingException;

public interface EmailSender {

    void sendEmail(User user);
}
