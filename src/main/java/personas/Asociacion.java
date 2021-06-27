package personas;

import mascotas.PublicacionMascotaEnAdopcion;
import mascotas.PublicacionMascotaPerdida;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Asociacion {
  private final List<PublicacionMascotaPerdida> publicacionesDeMascotasPerdidas = new ArrayList<>();
  private final List<PublicacionMascotaEnAdopcion> publicacionesDeMascotasEnAdopcion = new ArrayList<>();
  private final List<Pregunta> preguntas = new ArrayList<>();
  private final Posicion posicion;
  // TODO : HECHO Posicion posicion;

  public Asociacion(Posicion posicion) {
    this.posicion = posicion;
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
    return this.preguntas.stream().filter(p -> p.getCuerpo().contains(cadena)).collect(Collectors.toList());
  }


}
