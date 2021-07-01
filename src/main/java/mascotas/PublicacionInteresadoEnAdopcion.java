package mascotas;

import exceptions.NoHayRespuestaException;
import personas.Interesado;
import personas.Respuesta;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PublicacionInteresadoEnAdopcion {

  private final List<Respuesta> respuestas = new ArrayList<>();
  private final Interesado interesado;

  public PublicacionInteresadoEnAdopcion(Interesado interesado) {
    this.interesado = interesado;
  }

  public void notificacionSemanal() {
    interesado.contactarDuenioPorSugerencia();
  }

  public void agregarRespuesta(Respuesta respuesta) {
    this.respuestas.add(respuesta);
  }

  public Boolean coincideRespuesta(Respuesta respuesta) {
    Optional<Respuesta> respuestaPropia = this.respuestas.stream().filter(
        unaRespuesta -> Objects.equals(unaRespuesta.getPregunta(), respuesta.getPregunta()))
        .findFirst();
    if(respuestaPropia.isPresent()) {
      return respuestaPropia.get().getRespuesta() == respuesta.getRespuesta();
    } else {
      throw new NoHayRespuestaException("El usuario no respondio la pregunta");
    }
  }
}
