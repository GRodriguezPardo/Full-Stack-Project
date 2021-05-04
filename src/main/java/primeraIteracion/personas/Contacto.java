package primeraIteracion.personas;

public class Contacto {
  private final String nombreApellido;
  private final Integer telefono;
  private final String email;


  public Contacto(String _nombreApellido,
                  Integer _telefono,
                  String _email) {
    this.nombreApellido = _nombreApellido;
    this.telefono = _telefono;
    this.email = _email;
  }

  public String getNombreApellido() {
    return nombreApellido;
  }

  public Integer getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }
}
