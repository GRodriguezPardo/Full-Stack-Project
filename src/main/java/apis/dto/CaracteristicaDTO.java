package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaracteristicaDTO {

  public CaracteristicaDTO(){
  }

  @Override
  public String toString(){
    return "Manso";
  }
}
