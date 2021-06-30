package personas;

import mascotas.Mascota;

public class Usuario extends Perfil {
  private final Duenio duenio;

  public Usuario(String usuario, String clave, Duenio _duenio) {
    super(usuario, clave);
    this.duenio = _duenio;
  }

  public Boolean duenioDe(Mascota mascota){
      return duenio.tieneMascota(mascota);
  }


  public Duenio getDuenio() {
    return this.duenio;
  }
}
