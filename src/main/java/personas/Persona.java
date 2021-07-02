package personas;

import apis.MedioNotificacion;
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
  private final List<MedioNotificacion> mediosNotificacion = new ArrayList<>();

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
                 MedioNotificacion _medioNotificacion) {
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
    if (Objects.isNull(_medioNotificacion)) {
      throw new FaltanDatosException(
              "Se debe proveer minimo un medio de notificacion"
      );
    }
    this.nombreYApellido = _nombreYApellido;
    this.fechaNacimiento = _fechaNacimiento;
    this.contactos.addAll(_contacto);
    this.agregarMedioNotificacion(_medioNotificacion);
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

  //TODO : HECHO metodo preferido, hardcodear mensaje,
  public void contactarPorMascota() {
    this.contactos
            .forEach(unContacto -> {
              this.mediosNotificacion.forEach(m -> m.notificarMascotaPerdida(unContacto));
            });
  }

  public void contactarPorInteresado() {
    this.contactos
            .forEach(unContacto -> {
              this.mediosNotificacion.forEach(m -> m.notificarInteresEnAdopcion(unContacto));
            });
  }


  public void contactarPorSugerenciaSemanal(Integer cantidad) {
    this.contactos
            .forEach(unContacto -> {
              this.mediosNotificacion.forEach(m -> m.notificarSugerenciaSemanal(unContacto, cantidad));
            });
  }

  public void agregarMedioNotificacion(MedioNotificacion medioNotificacion) {
    this.mediosNotificacion.add(medioNotificacion);
  }

  public void removerMedioNotificacion(MedioNotificacion medioNotificacion) {
    this.mediosNotificacion.remove(medioNotificacion);
  }

  public void contactarPorMailBaja() {
    this.contactos
        .forEach(unContacto -> {
          this.mediosNotificacion.forEach(m -> m.notificarMailDeBaja(unContacto));
        });
  }
}
