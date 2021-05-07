package primeraIteracion.personas;

public abstract class Perfil {
  private String clave;

  public Perfil(String _clave) {
    this.clave = _clave;
  }
  public String getClave() {
    return clave;
  }

  public void setClave(String _clave) {
    this.clave = _clave;
  }
}

