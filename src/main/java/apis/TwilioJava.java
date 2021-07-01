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
  public void sendSms(String destinationNumber, String mensaje) {
    Twilio.init(this.accountSid, this.authToken);
    Message.creator(new PhoneNumber(destinationNumber),
            new PhoneNumber(this.senderNumber),mensaje).create();
  }

  public void notificarMascotaPerdida(Contacto contacto) {
    this.sendSms(contacto.getTelefono(),"Encontrasmos a tu mascota perdida");
  }

  @Override
  public void notificarInteresEnAdopcion(Contacto contacto) {
    this.sendSms(contacto.getTelefono(),"Hay interesados en adoptar a tu mascota");
  }

  @Override
  public void notificarSugerenciaSemanal(Contacto contacto, Integer cantidad) {
    String cuerpo;

    if(cantidad > 0) {
      cuerpo = "Tenemos " + cantidad + " sugerecias de tu interes";
    } else {
      cuerpo = "Esta semana no tenemos sugerencias para vos!\n"
          + "Te recomendamos que entres igual a ver las mascotas que estan "
          + "esperando a un nuevo dueño!";
    }
    this.sendSms(contacto.getTelefono(),cuerpo);
  }
}
