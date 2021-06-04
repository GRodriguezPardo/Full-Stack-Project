package personas;

import mascotas.PublicacionMascotaPerdida;

import java.util.ArrayList;
import java.util.List;

public class Asociacion {
  private final List<PublicacionMascotaPerdida> publicacionesACargo = new ArrayList<>();
  private final Integer latitud;
  private final Integer longitud;
  // TODO : Posicion posicion;

  public Asociacion(Integer latitud, Integer longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public List<PublicacionMascotaPerdida> publicacionesACargo() {
    return this.publicacionesACargo;
  }

  public void agregarPublicacion(PublicacionMascotaPerdida publicacion) {
    this.publicacionesACargo.add(publicacion);
  }

  public Integer distanciaA(Integer latitud, Integer longitud) {
    Integer resultadoLongitud = this.longitud - longitud;
    Integer resultadoLatitud = this.latitud - latitud;
    return (int) Math.sqrt(Math.pow(resultadoLatitud, 2) + Math.pow(resultadoLongitud, 2));
  }
}
