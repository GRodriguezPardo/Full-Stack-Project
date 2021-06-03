package mascotas;

import personas.Rescatista;

public class PublicacionMascotaPerdida {
  private Rescatista rescatista;
  private Boolean aprobado;
  private Integer latitud;
  private Integer longitud;

  public PublicacionMascotaPerdida(Rescatista _rescatista, Integer latitud, Integer longitud) {
    this.rescatista = _rescatista;
    this.latitud = latitud;
    this.longitud = longitud;
    this.aprobado = false;
  }

  public void aprobar(){
    this.aprobado = true;
  }

  public Boolean aprobado() {
    return this.aprobado;
  }

  public Integer getLatitud() {
    return latitud;
  }

  public Integer getLongitud() {
    return longitud;
  }
}
