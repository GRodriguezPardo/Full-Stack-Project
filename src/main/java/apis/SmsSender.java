package apis;

public interface SmsSender {

  void sendSms(String destinationNumber, String message);

}
