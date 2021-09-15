package mascotas;

import exceptions.NoHayRespuestaException;
import personas.Persona;
import personas.Respuesta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PublicacionInteresadoEnAdopcion {

  private final List<Respuesta> respuestas = new ArrayList<>();
  Persona persona;

  private Integer acumuladorRecomendaciones = 0;

  public PublicacionInteresadoEnAdopcion(Persona persona) {
    this.persona = persona;
  }

  public void notificarMailDeBaja(){
    this.persona.contactarPorMailBaja();
  }

  public void notificacionSemanal() {
    this.persona.contactarPorSugerenciaSemanal(acumuladorRecomendaciones);
    this.acumuladorRecomendaciones = 0;
  }

  public void agregarRecomendacion() {
    this.acumuladorRecomendaciones += 1;
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
