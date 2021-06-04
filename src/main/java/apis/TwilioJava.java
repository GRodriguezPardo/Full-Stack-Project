package apis;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



public class TwilioJava implements SmsSender {
  public String accountSid;
  public String authToken;
  public String senderNumber;
  /*Se debe crear una cuenta en https://www.twilio.com y , en el dashboard , estan las credenciales a setear.
  La trial tiene mensajes limitados , por eso no pongo la que cree.La paga es ilimitada.
  Al acabarse los mensajes se debe setear otra cuenta trial con otros datos de registro o pagar la full.
  El senderNumber se obtiene en le dashboard , con el boton rojo que genera un numero para enviar mensajes ,
   el del registro no sirve.*/

  /*Ese numero de destino debe estar verificado en la pagina y debe escribirse igual que como aparece en ella al verificarlo ahi. */
  public void sendSMS(String destinationNumber, String message) {
    if (this.authToken != null && this.accountSid != null && this.senderNumber != null) {
      Twilio.init(this.accountSid, this.authToken);
      Message sms = Message.creator(new PhoneNumber(destinationNumber),
          new PhoneNumber(this.senderNumber),
          message).create();
    }
  }

  /*Estos atributos deben setearse juntos porque van de a 2 porque corresponden a una
  cuenta creada en la plataforma*/
  public void setAccountSidAndAuthToken(String id, String token) {
    this.accountSid = id;
    this.authToken = token;
  }

  public void setSenderNumber(String number) {
    this.senderNumber = number;
  }

}
