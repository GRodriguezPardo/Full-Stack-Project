package personas;

import persistence.PersistenceId;

import javax.persistence.*;

@MappedSuperclass
public abstract class Perfil extends PersistenceId{

  @Column
  private String usuario;

  @Column
  private String clave;

  public Perfil(String _usuario, String _clave) {
    this.usuario = _usuario;
    this.clave = _clave;
  }

  protected Perfil() {}


  public long getId() {
    return id;
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

}




