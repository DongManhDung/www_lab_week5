package iuh.edu.vn.backend.services;

public interface IEmail {
    void sendEmail(String to, String subject, String body);
}
