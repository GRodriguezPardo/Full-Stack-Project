package apis;

public interface EmailSender {

  void sendEmail(String destination, String message);
}
