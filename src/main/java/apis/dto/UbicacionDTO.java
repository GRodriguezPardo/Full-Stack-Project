package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UbicacionDTO {

  private String direccion;
  private double lat;
  private double Long;

  public UbicacionDTO() {
  }

  public double getLong() {
    return Long;
  }

  public void setLong(double aLong) {
    Long = aLong;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  @Override
  public String toString() {
    return "{"
            + '"' + "dirección" + '"' + ": " + getDireccion() + ','
            + '"' + "lat" + '"' + ": " + getLat() + ','
            + '"' + "long" + '"' + ": " + getLong()
            + "}";
  }
}
