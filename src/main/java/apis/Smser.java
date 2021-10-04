package apis;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import exceptions.FaltanDatosException;
import persistence.PersistenceId;

import javax.persistence.Entity;

// TODO: Podriamos poner un nombre mas representativo SmsService
@Entity
public class Smser extends PersistenceId {
  public String accountSid;
  public String authToken;
  public String senderNumber;

  public Smser(String id, String token, String number) {
    this.accountSid = id;
    this.authToken = token;
    this.senderNumber = number;
    if (this.authToken == null || this.accountSid == null || this.senderNumber == null) {
      throw new FaltanDatosException("Falta indicar algun dato de los 3 pedidos");
    }
  }
  /*Ese numero de destino debe estar verificado en la pagina y debe escribirse igual que como aparece en ella al verificarlo ahi. */
  public void sendSms(String destinationNumber, String mensaje) {
    Twilio.init(this.accountSid, this.authToken);
    Message.creator(new PhoneNumber(destinationNumber),
            new PhoneNumber(this.senderNumber), mensaje).create();
  }
}
