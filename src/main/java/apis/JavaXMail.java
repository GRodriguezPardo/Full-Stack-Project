package apis;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class JavaXMail implements EmailSender {

  private String remitente = "unemailejemplar";
  private String clave = "HolaComoEstas";

  public void sendEmail(String destinatario, String subject, String message) throws MessagingException {

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

    unMensaje.setFrom(new InternetAddress(remitente));

    unMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
    unMensaje.setSubject(subject);
    unMensaje.setText(message);

    Transport t = session.getTransport("smtp");
    t.connect("smtp.gmail.com", remitente, clave);
    t.sendMessage(unMensaje, unMensaje.getAllRecipients());
    t.close();
  }

  public void setearNuevoRemitente(String remitente,String clave){
    this.remitente = remitente;
    this.clave = clave;
  }

}
