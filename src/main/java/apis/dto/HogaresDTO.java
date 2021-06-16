package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HogaresDTO {
  private int total;
  private String offset;
  private List<HogarDTO> hogares;

  public HogaresDTO(){
    hogares = new ArrayList<>();
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public String getOffset() {
    return offset;
  }

  public void setOffset(String offset) {
    this.offset = offset;
  }

  public List<HogarDTO> getHogares() {
    return hogares;
  }

  public void setHogares(List<HogarDTO> hogares) {
    this.hogares = hogares;
  }

  @Override
  public String toString() {
    return ("{" + '"' +"total" + '"' + ':' + getTotal() + ',' +
            '"' + "offset"  + '"' + ':' + getOffset() + ',' +
            '"' + "hogares" + '"' + ':' + getHogares() +
            '}');
  }
}
