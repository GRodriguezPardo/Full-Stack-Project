package primeraIteracion.personas;

public abstract class Perfil {
  String clave;
  public Perfil(String _clave) {
    this.clave = _clave;
  }
  public String getClave() {
    return clave;
  }

  public void setContrasenia(String _clave) {
    this.clave = _clave;
  }
}

