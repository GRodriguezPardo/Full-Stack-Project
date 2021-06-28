package personas;

// TODO : Podemos borrarla y dejar en standby su existencia

import mascotas.Mascota;

public class Admin extends Perfil {
  public Admin(String usuario, String clave) {
    super(usuario, clave);
  }

  public Boolean duenioDe(Mascota mascota){
    return false;
  }

  @Override
  public Duenio getDuenio() {
    return null;
  }
}
