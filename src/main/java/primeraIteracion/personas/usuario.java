package primeraIteracion.personas;

public class usuario extends Perfil {
  private final Duenio duenio;

  public usuario(String _clave, Duenio _duenio) {
    super(_clave);
    this.duenio = _duenio;
  }

  public Duenio getDuenio() {
    return this.duenio;
  }
}
