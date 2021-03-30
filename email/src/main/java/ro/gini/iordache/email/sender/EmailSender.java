package ro.gini.iordache.email.sender;

import com.gini.iordache.entity.user.User;

public interface EmailSender {

    void sendEmail(User user);
}
