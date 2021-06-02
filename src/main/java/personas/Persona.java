package personas;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import apis.EmailSender;
import apis.SmsSender;
import exceptions.FaltanDatosException;

/**
 * Clase abstracta que aglomera los comportamientos en comun de las personas.
 */
public class Persona {
  private String nombreYApellido;
  private LocalDate fechaNacimiento;
  private List<Contacto> contactos = new ArrayList<>();
  private EmailSender emailSender;
  private SmsSender smsSender;

  /**
   * Constructor de la clase.
   *
   * @param _nombreYApellido es el nombre y apellido de la persona.
   * @param _fechaNacimiento es la fecha de nacimiento de la persona.
   * @param _contacto es la lista de formas de contacto de la persona.
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

  public void contactarSobreMascotaEncontrada(String message) {
    this.contactos
        .forEach(unContacto -> {
          this.emailSender.sendEmail(unContacto.getEmail(), message);
          this.smsSender.sendSMS(unContacto.getTelefono(), message);
        });
  }
}
