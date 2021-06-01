package personas;

public class Usuario extends Perfil {
  private final Duenio duenio;

  public Usuario(String usuario, String clave, Duenio _duenio) {
    super(usuario, clave);
    this.duenio = _duenio;
  }

  public Duenio getDuenio() {
    return this.duenio;
  }
}
