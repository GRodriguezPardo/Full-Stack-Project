import apis.JavaXMail;
import apis.Smser;
import apis.Mailer;
import apis.TwilioJava;
import mascotas.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import personas.Contacto;
import repositorios.RepositorioDeCaracteristicas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NotificacionesTest {
  Fixture fixture = new Fixture();

  @BeforeAll
  public static void agregarPosiblesCaracteristicas() {
    RepositorioDeCaracteristicas.getInstance().agregarPosibleCaracteristica("Color principal");//, new Caracteristica<String>());
    RepositorioDeCaracteristicas.getInstance().agregarPosibleCaracteristica("Esta castrado");//, new Caracteristica<Boolean>());
  }

  @Test
  @Disabled
  public void enviarMailNoTiraErrorTESTMANUAL() {
    Mailer emailSender = new Mailer(new JavaXMail("unemailejemplar", "HolaComoEstas"));// _________\/__________ aca pones el mailDestinatario y te fijas que llegue
    assertDoesNotThrow(() -> emailSender.getJavax().sendEmail("unEjemplo@gmail.com", "Encontramos a tu mascota", "Ejemplo"));
  }

  @Test
  @Disabled
  public void enviarSmsNoTiraErrorTESTMANUAL() {
    Smser sender = new Smser(new TwilioJava( null, "a", "11"));//LEER comentarios en smsSender para probar posta con tu telefono
    Contacto contacto = new Contacto("Anonimo", "+541165919737", "messi@messi.com");
    assertDoesNotThrow(() -> sender.notificarMascotaPerdida(contacto));
  }  /*Ahi pones un numero destinatario verificado en la pagina (ese lo esta pero no vas a ver el mensaje , es para mostrar el formato
  valido del numero) y te fijas que te llege el mensaje*/

}
