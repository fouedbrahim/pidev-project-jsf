package ttma.mBean;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.MessagingException;
import util.MailService;

/**
 * A bean class that illustrates sending an email message
 * @author tcolburn
 */
@Named
@RequestScoped
public class ClientBean {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    
    public String send() {
        statusMessage = "Message Sent";
        try {
            MailService.sendMessage(recipient, subject, message);
        }
        catch(MessagingException ex) {
            statusMessage = ex.getMessage();
        }
        return "send_mail";  // redisplay page with status message
    }
    
    private String recipient;
    private String subject;
    private String message;
    private String statusMessage = "";
    
}