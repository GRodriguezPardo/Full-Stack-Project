package personas;

import javax.persistence.Entity;

@Entity
public class Admin extends Perfil {
  public Admin(String usuario, String clave) {
    super(usuario, clave);
  }

}
