package personas;

import mascotas.PublicacionMascotaPerdida;

import javax.persistence.*;
import java.util.List;

@Entity
public class Voluntario extends Perfil {

  @ManyToOne(cascade = CascadeType.ALL)
  private final Asociacion asociacion;

  public Voluntario(String _usuario, String _clave, Asociacion asociacion) {
    super(_usuario, _clave);
    this.asociacion = asociacion;
  }

  public List<PublicacionMascotaPerdida> publicacionesGestionables() {
    return this.asociacion.publicacionesACargo();
  }

}
