package apis;

public interface EmailSender {

  void sendEmail(String destinatario, String subject, String message);

}
