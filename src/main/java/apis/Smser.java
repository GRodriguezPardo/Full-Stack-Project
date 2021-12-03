package apis;

import personas.Contacto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

// TODO: Podriamos poner un nombre mas representativo SmsService
@Entity
@DiscriminatorValue("SMS")
public class Smser extends MedioNotificacion {

  @Transient
  public TwilioJava twilio;
  /*Se debe crear una cuenta en https://www.twilio.com y , en el dashboard , estan las credenciales a setear.
  La trial tiene mensajes limitados , por eso no pongo la que cree.La paga es ilimitada.
  Al acabarse los mensajes se debe setear otra cuenta trial con otros datos de registro o pagar la full.
  El senderNumber se obtiene en le dashboard , con el boton rojo que genera un numero para enviar mensajes ,
   el del registro no sirve.*/

  public Smser(TwilioJava twilio) {
    this.twilio = twilio;
  }

  public Smser() {
  }//pedirle la instancia a un service locator

  /*Ese numero de destino debe estar verificado en la pagina y debe escribirse igual que como aparece en ella al verificarlo ahi. */
  /*public void sendSms(String destinationNumber, String mensaje) {
    Twilio.init(this.accountSid, this.authToken);
    Message.creator(new PhoneNumber(destinationNumber),
            new PhoneNumber(this.senderNumber), mensaje).create();
  }*/

  public void notificarMascotaPerdida(Contacto contacto) {
   // this.twilio.sendSms(contacto.getTelefono(), "Encontrasmos a tu mascota perdida");
  }

  @Override
  public void notificarInteresEnAdopcion(Contacto contacto) {
    this.twilio.sendSms(contacto.getTelefono(), "Hay interesados en adoptar a tu mascota");
  }

  @Override
  public void notificarSugerenciaSemanal(Contacto contacto, Integer cantidad) {
    String cuerpo;

    if (cantidad > 0) {
      cuerpo = "Tenemos " + cantidad + " sugerecias de tu interes";
    } else {
      cuerpo = "Esta semana no tenemos sugerencias para vos!\n"
              + "Te recomendamos que entres igual a ver las mascotas que estan "
              + "esperando a un nuevo dueño!";
    }
    this.twilio.sendSms(contacto.getTelefono(), cuerpo);
  }

  @Override
  public void notificarMailDeBaja(Contacto contacto) {

  }
}
