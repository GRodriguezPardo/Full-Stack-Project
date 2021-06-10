package personas;

public class Posicion {
    private int longitud;
    private int latitud;

  public Posicion(int longitud, int latitud) {
    this.longitud = longitud;
    this.latitud = latitud;
  }

  public Integer distanciaA(Posicion posicion) {
    int resultadoLongitud = this.longitud - posicion.longitud;
    int resultadoLatitud = this.latitud - posicion.latitud;
    return (int) Math.sqrt(Math.pow(resultadoLatitud, 2) + Math.pow(resultadoLongitud, 2));
  }
}
