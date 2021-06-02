package apis;

public interface SmsSender {

  void sendSMS(Integer destination, String message);
}
