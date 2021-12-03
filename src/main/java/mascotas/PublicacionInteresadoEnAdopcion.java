package mascotas;

import exceptions.NoHayRespuestaException;
import persistence.PersistenceId;
import personas.Persona;
import personas.Respuesta;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class PublicacionInteresadoEnAdopcion extends PersistenceId {

  @OneToMany
  private final List<Respuesta> respuestas = new ArrayList<>();

  @ManyToOne
  public Persona persona;

  public PublicacionInteresadoEnAdopcion(Persona persona) {
    this.persona = persona;
  }

  public void notificarMailDeBaja() {
    this.persona.contactarPorMailBaja();
  }

  public void notificacionSemanal(long cantidad) {
    this.persona.contactarPorSugerenciaSemanal((int) cantidad);
  }

  public void agregarRespuesta(Respuesta respuesta) {
    this.respuestas.add(respuesta);
  }

  public Boolean coincideRespuesta(Respuesta respuesta) {
    Optional<Respuesta> respuestaPropia = this.respuestas.stream().filter(
            unaRespuesta -> unaRespuesta.coincide(respuesta))
            .findFirst();
    if (respuestaPropia.isPresent()) {
      return Objects.equals(respuestaPropia.get().getRespuesta(), respuesta.getRespuesta());
    } else {
      throw new NoHayRespuestaException("El usuario no respondio la pregunta");
    }
  }
}
