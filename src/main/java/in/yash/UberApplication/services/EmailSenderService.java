package in.yash.UberApplication.services;

public interface EmailSenderService {
    
    void sendEmail(String toEmail,String subject,String body);
    
}
