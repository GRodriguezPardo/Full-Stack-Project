package apis;

import personas.Contacto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Mail")
public class JavaXMail extends MedioNotificacion {

  @OneToOne
  public Mailer mailer;

  public JavaXMail(Mailer mailer) {
    this.mailer =mailer;
  }

  public void notificarMascotaPerdida(Contacto contacto) {
    this.mailer.sendEmail(contacto.getEmail(), "Sistemas de Rescates", "Encontramos a tu mascota perdida");
  }

  @Override
  public void notificarInteresEnAdopcion(Contacto contacto) {
    this.mailer.sendEmail(contacto.getEmail(),
            "Hay interesado en tu mascota!!",
            "Encontramos interesados en tu mascota");
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

    this.mailer.sendEmail(contacto.getEmail(),
            "Sugerencias Semanales",
            cuerpo);

  }

  @Override
  public void notificarMailDeBaja(Contacto unContacto) {
    this.mailer.sendEmail(unContacto.getEmail(),
        "Baja del Sistema", "https:\\\\patitas.com\\darseDeBaja");
  }

  public Mailer getMailer(){
    return this.mailer;
  }

}
