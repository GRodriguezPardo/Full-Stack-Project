package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdmisionesDTO {

  private boolean gatos = false;
  private boolean perros = false;

  public AdmisionesDTO() {
  }

  public boolean getGatos() {
    return gatos;
  }

  public void setGatos(boolean gatos) {
    this.gatos = gatos;
  }

  public boolean getPerros() {
    return perros;
  }

  public void setPerros(boolean perros) {
    this.perros = perros;
  }


  @Override
  public String toString() {
    return  "{"
        + '"' + "perros"  + '"' + ": " + getPerros() + ','
        + '"' + "gatos"  + '"' + ": " + getGatos()
        + "}";
  }
}
