package ro.gini.iordache.email.sender;

public interface EmailHtmlRenderer {
    String constructHtmlMailPage(String link, String username);
}
