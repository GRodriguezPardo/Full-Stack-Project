package personas;

import mascotas.Mascota;
import mascotas.PublicacionMascotaPerdida;

import java.util.List;

public class Voluntario extends Perfil {
  private final Asociacion asociacion;

  public Voluntario(String _usuario, String _clave, Asociacion asociacion) {
    super(_usuario, _clave);
    this.asociacion = asociacion;
  }

  public List<PublicacionMascotaPerdida> publicacionesGestionables() {
    return this.asociacion.publicacionesACargo();
  }

  public Boolean duenioDe(Mascota mascota){
    return false;
  }

  @Override
  public Duenio getDuenio() {
    return null;
  }

}
