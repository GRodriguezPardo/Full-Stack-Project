package personas;

import apis.EmailSender;
import apis.SmsSender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder de la clase persona que aglomera los comportamientos de los builders
 * de clases que heredan de la clase persona.
 */
public class PersonaBuilder {
  protected String nombreYApellido;
  protected LocalDate fechaNacimiento;
  protected List<Contacto> contactos = new ArrayList<>();
  private EmailSender emailSender;
  private SmsSender smsSender;
  /**
   * Constructor vacio propio de un builder sin variables.
   */
  public PersonaBuilder() {
  }

  /**
   * Setter del nombre y apellido.
   *
   * @param _nombreYApellido nombre y apellido a settear
   */
  public void setNombreYApellido(String _nombreYApellido) {
    this.nombreYApellido = _nombreYApellido;
  }

  /**
   * Setter de la fecha de nacimiento.
   *
   * @param _fechaNacimiento fecha de nacimiento a settear.
   */
  public void setFechaNacimiento(LocalDate _fechaNacimiento) {
    this.fechaNacimiento = _fechaNacimiento;
  }

  /**
   * Agrega una forma de contacto.
   *
   * @param _contacto forma de contacto a agregar.
   */
  public void agregarContacto(Contacto _contacto) {
    this.contactos.add(_contacto);
  }

   /**
    * Agrega la dependencia de emailSender que se inyectara
    *
    * @param _emailSender
    */
  public void agregarEmailSender(EmailSender _emailSender) {
    this.emailSender = _emailSender;
  }

  /**
    * Agrega la dependencia de smsSender que se inyectara
    *
    * @param _smsSender
    */
  public void agregarSmsSender(SmsSender _smsSender) {
    this.smsSender = _smsSender;
  }

  /**
   * Finaliza la creacion de la persona.
   * Hace los chequeos de los atributos necesarios.
   * Deja la creacion final a sus clases hijo.
   *
   * @return retorna la persona creada.
   */
  public Persona crearPersona() {
    return new Persona(this.nombreYApellido, this.fechaNacimiento, this.contactos, this.emailSender, this.smsSender);
  }
}
