package personas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Perfil {
  public Admin(String usuario, String clave) {
    super(usuario, clave);
  }

}
