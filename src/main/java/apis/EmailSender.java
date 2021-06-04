package apis;

import javax.mail.MessagingException;

public interface EmailSender {

  void sendEmail(String destinatario, String subject, String message) throws MessagingException;

}
