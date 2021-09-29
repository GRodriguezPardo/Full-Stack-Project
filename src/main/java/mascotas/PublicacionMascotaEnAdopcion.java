package mascotas;


import persistence.PersistenceId;
import personas.Duenio;
import personas.Respuesta;
import repositorios.RepositorioDeUsuarios;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PublicacionMascotaEnAdopcion extends PersistenceId{

  @Transient
  private final Mascota mascota;
  @Transient
  private final List<Respuesta> respuestas = new ArrayList<>();

  public PublicacionMascotaEnAdopcion(Mascota mascota) {
    this.mascota = mascota;
  }

  public Mascota getMascota() {
    return mascota;
  }

  public void notificarADuenoPorInteresado() {
    this.duenioDePublicacion().contactarDuenioPorInteresado();
  }

  public Duenio duenioDePublicacion() {
    return RepositorioDeUsuarios.getInstance().usuarioDuenioDe(this.mascota);
  }

  public void agregarRespuesta(Respuesta respuesta) {
    this.respuestas.add(respuesta);
  }

  public List<Respuesta> getRespuestas() {
    return this.respuestas;
  }
}
