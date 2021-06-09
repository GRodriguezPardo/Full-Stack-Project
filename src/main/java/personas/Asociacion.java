package personas;

import mascotas.PublicacionMascotaPerdida;

import java.util.ArrayList;
import java.util.List;

public class Asociacion {
  private final List<PublicacionMascotaPerdida> publicacionesACargo = new ArrayList<>();
  private Posicion posicion;
  // TODO : HECHO Posicion posicion;

  public Asociacion(Posicion posicion) {
    this.posicion = posicion;
  }

  public List<PublicacionMascotaPerdida> publicacionesACargo() {
    return this.publicacionesACargo;
  }

  public void agregarPublicacion(PublicacionMascotaPerdida publicacion) {
    this.publicacionesACargo.add(publicacion);
  }

  public Integer distanciaA(Posicion unaPos) {
    return unaPos.distanciaA(this.posicion);
  }
}
