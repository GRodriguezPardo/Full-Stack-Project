package personas;

import persistence.PersistenceId;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 * Clase que representa una forma de contacto.
 * Toda forma de contacto debe poseer un nombre y apellido, telefono e email.
 */
@Embeddable
public class Contacto extends PersistenceId {
  private final String nombreApellido;
  private final String telefono;
  private final String email;
  //private final PreferenciaContacto metodo;

  /**
   * Constructor de la clase.
   *
   * @param _nombreApellido es el nombre y apellido de quien es contactado.
   * @param _telefono       es el telefono de contacto.
   * @param _email          es el email de contacto.
   */
  public Contacto(String _nombreApellido,
                  String _telefono,
                  String _email) {
    this.nombreApellido = _nombreApellido;
    this.telefono = _telefono;
    this.email = _email;
  }

  /**
   * Getter del nombre y apellido.
   *
   * @return retorna el nombre ya apellido.
   */
  public String getNombreApellido() {
    return nombreApellido;
  }

  /**
   * Getter del telefono.
   *
   * @return retorna el telefono.
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Getter del email.
   *
   * @return retorna el email.
   */
  public String getEmail() {
    return email;
  }
}
