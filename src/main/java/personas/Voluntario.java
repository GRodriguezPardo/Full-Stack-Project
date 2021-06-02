package personas;

import mascotas.Mascota;

public class Voluntario extends Perfil{

  public Voluntario(String _usuario, String _clave) {
    super(_usuario, _clave);
  }

  @Override
  public Boolean duenioDe(Mascota unaMascota) {
    return false;
  }
}
