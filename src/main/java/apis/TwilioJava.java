package apis;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import exceptions.FaltanDatosException;
import personas.Contacto;


public class TwilioJava implements MedioNotificacion {
  public String accountSid;
  public String authToken;
  public String senderNumber;
  /*Se debe crear una cuenta en https://www.twilio.com y , en el dashboard , estan las credenciales a setear.
  La trial tiene mensajes limitados , por eso no pongo la que cree.La paga es ilimitada.
  Al acabarse los mensajes se debe setear otra cuenta trial con otros datos de registro o pagar la full.
  El senderNumber se obtiene en le dashboard , con el boton rojo que genera un numero para enviar mensajes ,
   el del registro no sirve.*/

  public TwilioJava(String id, String token, String number) {
    this.accountSid = id;
    this.authToken = token;
    this.senderNumber = number;
    if (this.authToken == null || this.accountSid == null || this.senderNumber == null) {
      throw new FaltanDatosException("Falta indicar algun dato de los 3 pedidos");
    }
  }

  /*Ese numero de destino debe estar verificado en la pagina y debe escribirse igual que como aparece en ella al verificarlo ahi. */
  public void sendSms(String destinationNumber) {
    Twilio.init(this.accountSid, this.authToken);
    Message.creator(new PhoneNumber(destinationNumber),
            new PhoneNumber(this.senderNumber),
            "Se encontro a su mascota").create();
  }

  public void notificar(Contacto contacto) {
    this.sendSms(contacto.getTelefono());
  }
}
