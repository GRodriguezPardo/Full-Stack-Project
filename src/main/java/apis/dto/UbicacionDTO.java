package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UbicacionDTO {

  private String direccion;
  private String lat;
  //private String long;

  UbicacionDTO() {
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  @Override
  public String toString() {
    return "{" +
            "direccion:" + getDireccion() + '\'' +
            ", lat:" + getLat() + '\'' +
            "'}'";
  }
}
