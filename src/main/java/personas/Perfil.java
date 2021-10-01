package personas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Perfil {


  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  protected long id;

  @Column
  private String usuario;

  @Column
  private String clave;

  public Perfil(String _usuario, String _clave) {
    this.usuario = _usuario;
    this.clave = _clave;
  }

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




