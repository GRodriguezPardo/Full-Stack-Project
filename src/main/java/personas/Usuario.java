package personas;

import com.sun.org.apache.xpath.internal.operations.Bool;
import mascotas.Mascota;

public class Usuario extends Perfil {
  private final Duenio duenio;

  public Usuario(String usuario, String clave, Duenio _duenio) {
    super(usuario, clave);
    this.duenio = _duenio;
  }

  @Override
  public Boolean duenioDe(Mascota mascota){
      return duenio.tieneMascota(mascota);
  }


  public Duenio getDuenio() {
    return this.duenio;
  }
}
