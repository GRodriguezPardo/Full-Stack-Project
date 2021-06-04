package personas;

import apis.EmailSender;
import apis.SmsSender;
import exceptions.FaltanDatosException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase abstracta que aglomera los comportamientos en comun de las personas.
 */
public class Persona {
  private final String nombreYApellido;
  private final LocalDate fechaNacimiento;
  private final List<Contacto> contactos = new ArrayList<>();
  private final EmailSender emailSender;
  private final SmsSender smsSender;

  /**
   * Constructor de la clase.
   *
   * @param _nombreYApellido es el nombre y apellido de la persona.
   * @param _fechaNacimiento es la fecha de nacimiento de la persona.
   * @param _contacto        es la lista de formas de contacto de la persona.
   */
  public Persona(String _nombreYApellido,
                 LocalDate _fechaNacimiento,
                 List<Contacto> _contacto,
                 EmailSender _emailSender,
                 SmsSender _smsSender) {
    if (Objects.isNull(_nombreYApellido)
            || Objects.isNull(_fechaNacimiento)
            || Objects.isNull(_contacto)) {
      throw new FaltanDatosException(
              "Se debe proveer un nombre, una fecha de nacimiento y un contacto"
      );
    }
    if (_contacto.isEmpty()) {
      throw new FaltanDatosException(
              "Se debe proveer minimo un contacto"
      );
    }
    if (Objects.isNull(_emailSender)
            || Objects.isNull(_smsSender)) {
      throw new FaltanDatosException(
              "Falta proveer senders de email y sms"
      );
    }
    this.nombreYApellido = _nombreYApellido;
    this.fechaNacimiento = _fechaNacimiento;
    this.contactos.addAll(_contacto);
    this.emailSender = _emailSender;
    this.smsSender = _smsSender;
  }

  /**
   * Agrega una nueva forma de contacto.
   *
   * @param contacto es la forma de contacto a agregar.
   */
  public void agregarContacto(Contacto contacto) {
    this.contactos.add(contacto);
  }

  public String getNombreYApellido() {
    return this.nombreYApellido;
  }

  public LocalDate getFechaNacimiento() {
    return this.fechaNacimiento;
  }

  public List<Contacto> getContactos() {
    return this.contactos;
  }

  //TODO : metodo preferido, hardcodear mensaje,
  public void contactarPorMascota(String message) {
    this.contactos
            .forEach(unContacto -> {
              this.emailSender.sendEmail(unContacto.getEmail(), "Notificacion Mascota Perdida", message);
              this.smsSender.sendSMS(unContacto.getTelefono().toString(), message);
            });
  }
}
