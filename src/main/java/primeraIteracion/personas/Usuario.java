package primeraIteracion.personas;

public class Usuario extends Perfil {
  private final Duenio duenio;

  public Usuario(String _clave, Duenio _duenio) {
    super(_clave);
    this.duenio = _duenio;
  }

  public Duenio getDuenio() {
    return this.duenio;
  }
}
