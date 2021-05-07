package primeraIteracion.personas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import primeraIteracion.exceptions.FaltanDatosException;

/**
 * Clase abstracta que aglomera los comportamientos en comun de las personas.
 */
public abstract class Persona {
  private String nombreYApellido;
  private LocalDate fechaNacimiento;
  private List<Contacto> contactos = new ArrayList<>();

  /**
   * Constructor de la clase.
   *
   * @param _nombreYApellido es el nombre y apellido de la persona.
   * @param _fechaNacimiento es la fecha de nacimiento de la persona.
   * @param _contacto es la lista de formas de contacto de la persona.
   */
  public Persona(String _nombreYApellido,
                 LocalDate _fechaNacimiento,
                 List<Contacto> _contacto) {
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
    this.nombreYApellido = _nombreYApellido;
    this.fechaNacimiento = _fechaNacimiento;
    this.contactos.addAll(_contacto);
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
}
