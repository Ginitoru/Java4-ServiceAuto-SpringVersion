package ro.gini.iordache.email.sender;

public interface EmailSender {

    void sendEmail(String userEmail, String username, String token);
}
