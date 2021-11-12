package apis;

import personas.Contacto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("Mail")
public class Mailer extends MedioNotificacion {

  @Transient
  public JavaXMail javax;

  public Mailer(JavaXMail javax) {
    this.javax =javax;
  }

  public Mailer(){  ; }//pedirle la instancia a un service locator

  public void notificarMascotaPerdida(Contacto contacto) {
    this.javax.sendEmail(contacto.getEmail(), "Sistemas de Rescates", "Encontramos a tu mascota perdida");
  }

  @Override
  public void notificarInteresEnAdopcion(Contacto contacto) {
    this.javax.sendEmail(contacto.getEmail(),
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

    this.javax.sendEmail(contacto.getEmail(),
            "Sugerencias Semanales",
            cuerpo);

  }

  @Override
  public void notificarMailDeBaja(Contacto unContacto) {
    this.javax.sendEmail(unContacto.getEmail(),
        "Baja del Sistema", "https:\\\\patitas.com\\darseDeBaja");
  }

  public JavaXMail getJavax(){
    return this.javax;
  }

}
