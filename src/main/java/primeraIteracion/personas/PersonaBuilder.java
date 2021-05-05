package primeraIteracion.personas;

import primeraIteracion.exceptions.FaltanDatosException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Builder de la clase persona que aglomera los comportamientos de los builders
 * de clases que heredan de la clase persona.
 */
public abstract class PersonaBuilder {
  protected String nombreYApellido;
  protected LocalDate fechaNacimiento;
  protected List<Contacto> contactos = new ArrayList<>();

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
   * Finaliza la creacion de la persona.
   * Hace los chequeos de los atributos necesarios.
   * Deja la creacion final a sus clases hijo.
   *
   * @return retorna la persona creada.
   */
  public Persona crearPersona() {
    if(Objects.isNull(this.nombreYApellido)
       || Objects.isNull(this.fechaNacimiento)
       || this.contactos.isEmpty()){
      throw new FaltanDatosException(
          "Se debe proveer un nombre, una fecha de nacimiento y un contacto"
      );
    }
    return this.creacionEspecifica();
  }

  /**
   * Metodo abstracto que realiza los chequeos especificos de cada clase hijo,
   * luego crea la clase hijo y la retorna.
   *
   * @return retorna la persona creada.
   */
  public abstract Persona creacionEspecifica();
}
