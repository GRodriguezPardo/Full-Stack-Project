package personas;

import mascotas.Caracteristica;
import mascotas.Mascota;
import mascotas.PosiblesCaracteristicas;

public class Admin extends Perfil {
  public Admin(String usuario, String clave) {
    super(usuario, clave);
  }

  @Override
  public Boolean duenioDe(Mascota unaMascota) {
    return false;
  }
}
