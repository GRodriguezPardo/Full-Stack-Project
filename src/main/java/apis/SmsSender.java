package apis;

public interface SmsSender {

  void sendSMS(String destinationNumber, String message);

}
