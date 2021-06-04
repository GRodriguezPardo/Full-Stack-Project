package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdimisionesDTO {

  private boolean gato = false;
  private boolean perro = false;

  AdimisionesDTO() {
  }

  public boolean getGato() {
    return gato;
  }

  public void setGato(boolean gato) {
    this.gato = gato;
  }

  public boolean getPerro() {
    return perro;
  }

  public void setPerro(boolean perro) {
    this.perro = perro;
  }


  @Override
  public String toString() {
    return "{" +
            "gato:" + getGato() + '\'' +
            ", perro:" + getPerro() + '\'' +
            "'}'";
  }
}
