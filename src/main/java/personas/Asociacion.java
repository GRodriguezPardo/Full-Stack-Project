package personas;

import mascotas.PublicacionInteresadoEnAdopcion;
import mascotas.PublicacionMascotaEnAdopcion;
import mascotas.PublicacionMascotaPerdida;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {
  private final List<PublicacionMascotaPerdida> publicacionesDeMascotasPerdidas;
  private final List<PublicacionMascotaEnAdopcion> publicacionesDeMascotasEnAdopcion;
  private final List<PublicacionInteresadoEnAdopcion> publicacionInteresadoEnAdopcion;
  private final List<Pregunta> preguntas = new ArrayList<>();
  private final Posicion posicion;

  public Asociacion(Posicion posicion) {
    this.posicion = posicion;
    this.publicacionesDeMascotasPerdidas = new ArrayList<>();
    this.publicacionesDeMascotasEnAdopcion = new ArrayList<>();
    this.publicacionInteresadoEnAdopcion = new ArrayList<>();
  }

  public List<PublicacionMascotaPerdida> publicacionesACargo() {
    return this.publicacionesDeMascotasPerdidas;
  }

  public void agregarPublicacionMascotaPerdida(PublicacionMascotaPerdida publicacion) {
    this.publicacionesDeMascotasPerdidas.add(publicacion);
  }

  public void agregarPublicacionMascotaEnAdopcion(PublicacionMascotaEnAdopcion publicacion) {
    this.publicacionesDeMascotasEnAdopcion.add(publicacion);
  }

  public Integer distanciaA(Posicion unaPos) {
    return unaPos.distanciaA(this.posicion);
  }

  public void agregarPregunta(Pregunta pregunta) {
    if (!this.preguntas.contains(pregunta)) {
      this.preguntas.add(pregunta);
    }
  }

  public void eliminarPregunta(Pregunta pregunta) {
    this.preguntas.remove(pregunta);
  }

  public void borrarPreguntas() {
    this.preguntas.clear();
  }

  public List<Pregunta> getPreguntas() {
    return this.preguntas;
  }
  /*El metodo de abajo es para que la asociacion vea si ya hizo preguntas
  que esta pensando o tiene mejores o si no.*/

  public List<Pregunta> verPreguntasQueIncluyanCadena(String cadena) {
    return this.preguntas.stream()
            .filter(p -> p.getCuerpoDuenio().contains(cadena))
            .collect(Collectors.toList());
  }

  public List<PublicacionMascotaEnAdopcion> getPublicacionesEnAdopcion() {
    return this.publicacionesDeMascotasEnAdopcion;
  }

  public List<PublicacionInteresadoEnAdopcion> getPublicacionInteresadoEnAdopcion() {
    return this.publicacionInteresadoEnAdopcion;
  }

  public void agregarPublicacionInteresadoEnAdopcion(PublicacionInteresadoEnAdopcion publicacion) {
    this.publicacionInteresadoEnAdopcion.add(publicacion);
  }

  public void removerPublicacionInteresadoEnAdopcion(PublicacionInteresadoEnAdopcion publicacionInteresadoEnAdopcion) {
    this.publicacionInteresadoEnAdopcion.remove(publicacionInteresadoEnAdopcion);
  }

  public void removerPublicacionMascotaEnAdopcion(PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion) {
    this.publicacionesDeMascotasEnAdopcion.remove(publicacionMascotaEnAdopcion);
  }
}
