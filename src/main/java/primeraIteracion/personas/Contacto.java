package primeraIteracion.personas;

/**
 * Clase que representa una forma de contacto.
 * Toda forma de contacto debe poseer un nombre y apellido, telefono e email.
 */
public class Contacto {
  private final String nombreApellido;
  private final Integer telefono;
  private final String email;

  /**
   * Constructor de la clase.
   *
   * @param _nombreApellido es el nombre y apellido de quien es contactado.
   * @param _telefono es el telefono de contacto.
   * @param _email es el email de contacto.
   */
  public Contacto(String _nombreApellido,
                  Integer _telefono,
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
  public Integer getTelefono() {
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
