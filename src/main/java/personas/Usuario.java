package personas;

import mascotas.Mascota;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Usuario extends Perfil {
  @OneToOne(cascade = CascadeType.ALL)
  private final Duenio duenio;

  public Usuario(String usuario, String clave, Duenio _duenio) {
    super(usuario, clave);
    this.duenio = _duenio;
  }

  protected Usuario() {
    this.duenio = null;
  }

  public Boolean duenioDe(Mascota mascota) {
    return duenio.tieneMascota(mascota);
  }


  public Duenio getDuenio() {
    return this.duenio;
  }
}
