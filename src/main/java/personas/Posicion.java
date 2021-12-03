package personas;

import javax.persistence.Embeddable;

@Embeddable
public class Posicion {
  private final Double longitud;

  public Double getLongitud() {
    return longitud;
  }

  public Double getLatitud() {
    return latitud;
  }

  private final Double latitud;

  public Posicion(double longitud, double latitud) {
    this.longitud = longitud;
    this.latitud = latitud;
  }

  public Posicion() {
    this.longitud = new Double(0);
    this.latitud = new Double(0);
  }

  public Integer distanciaA(Posicion posicion) {
    double resultadoLongitud = this.longitud - posicion.longitud;
    double resultadoLatitud = this.latitud - posicion.latitud;
    return (int) Math.sqrt(Math.pow(resultadoLatitud, 2) + Math.pow(resultadoLongitud, 2));
  }
}
