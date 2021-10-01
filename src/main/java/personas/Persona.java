package personas;

import apis.MedioNotificacion;
import exceptions.FaltanDatosException;
import persistence.PersistenceId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Clase abstracta que aglomera los comportamientos en comun de las personas.
 */

@Entity
public class Persona extends PersistenceId {

  @Column
  private final String nombreYApellido;

  @Column
  private final LocalDate fechaNacimiento;

  @Embedded
  private final List<Contacto> contactos = new ArrayList<>();

  @ManyToMany
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

  protected void contactar(Consumer<MedioNotificacion> action) {
    this.mediosNotificacion.forEach(action);
  }

  public void contactarPorMascota() {
    this.contactar(medio ->
        this.contactos.forEach(unContacto -> medio.notificarMascotaPerdida(unContacto))
    );
  }

  public void contactarPorInteresado() {
    this.contactar(medio ->
        this.contactos.forEach(unContacto -> medio.notificarInteresEnAdopcion(unContacto))
    );
  }

  public void contactarPorSugerenciaSemanal(Integer cantidad) {
    this.contactar(medio ->
        this.contactos.forEach(unContacto -> medio.notificarSugerenciaSemanal(unContacto,cantidad))
    );
  }

  public void contactarPorMailBaja() {
    this.contactar(medio ->
        this.contactos.forEach(unContacto -> medio.notificarMailDeBaja(unContacto))
    );
  }

  public void agregarMedioNotificacion(MedioNotificacion medioNotificacion) {
    this.mediosNotificacion.add(medioNotificacion);
  }

  public void removerMedioNotificacion(MedioNotificacion medioNotificacion) {
    this.mediosNotificacion.remove(medioNotificacion);
  }
}
