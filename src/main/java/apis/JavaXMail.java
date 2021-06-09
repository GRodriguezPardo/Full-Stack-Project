package apis;

import exceptions.FalloServicioEmailException;
import personas.Contacto;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class JavaXMail implements EmailSender, MedioNotificacion {

  private String remitente;
  private String clave;

  public JavaXMail(String remitente, String clave) {
    this.remitente = remitente; //"unemailejemplar"
    this.clave = clave; //"HolaComoEstas"
  }

  public void notificar(Contacto contacto) {
    this.sendEmail(contacto.getEmail());
  }

  public void sendEmail(String destinatario) {

    Properties props = new Properties();

    // Nombre del host de correo, es smtp.gmail.com
    props.setProperty("mail.smtp.host", "smtp.gmail.com");

    // TLS si está disponible
    props.setProperty("mail.smtp.starttls.enable", "true");

    // Puerto de gmail para envio de correos
    props.setProperty("mail.smtp.port", "587");
    props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

    // Nombre y clave del usuario
    props.setProperty("mail.smtp.clave", clave);
    props.setProperty("mail.smtp.user", remitente);

    // Si requiere o no usuario y password para conectarse.
    props.setProperty("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props);
    //session.setDebug(true);

    MimeMessage unMensaje = new MimeMessage(session);

    try {
      unMensaje.setFrom(new InternetAddress(remitente));
      unMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
      unMensaje.setSubject("sistema de Rescates");
      unMensaje.setText("hemos encontrado a su mascota");
      sendRealMessage(session, unMensaje);
    } catch (Exception e) {
      throw new FalloServicioEmailException(e);
    }
  }

  // TODO : Hacer que el mock corra todos los metodos reales menos este.
  private void sendRealMessage(Session session, MimeMessage unMensaje) throws MessagingException {
    Transport t;
    t = session.getTransport("smtp");
    t.connect("smtp.gmail.com", remitente, clave);
    t.sendMessage(unMensaje, unMensaje.getAllRecipients());
    t.close();
  }

  public void setearNuevoRemitente(String remitente, String clave) {
    this.remitente = remitente;
    this.clave = clave;
  }

}
