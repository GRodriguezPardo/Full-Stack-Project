package personas;

import mascotas.Mascota;

public abstract class Perfil {
  private String usuario;
  private String clave;

  public Perfil(String _usuario, String _clave) {
    this.usuario = _usuario;
    this.clave = _clave;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String _clave) {
    this.clave = _clave;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public abstract Boolean duenioDe(Mascota mascota);

  public abstract Duenio getDuenio();
}




