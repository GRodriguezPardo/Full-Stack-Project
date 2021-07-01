package personas;

public class Posicion {
  private Double longitud;
  private Double latitud;

  public Posicion(double longitud, double latitud) {
    this.longitud = longitud;
    this.latitud = latitud;
  }

  public Integer distanciaA(Posicion posicion) {
    double resultadoLongitud = this.longitud - posicion.longitud;
    double resultadoLatitud = this.latitud - posicion.latitud;
    return (int) Math.sqrt(Math.pow(resultadoLatitud, 2) + Math.pow(resultadoLongitud, 2));
  }
}
