package apis.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value={"hogares", "id"})
public class HogaresDTO {

  private int total;
  private String offset;
  private List<HogarDTO> hogarList;


  public HogaresDTO(){
    hogarList = new ArrayList<>();
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

  @JsonGetter("hogares")
  public List<HogarDTO> getHogarList() {
    return hogarList;
  }

  public void setHogarList(List<HogarDTO> hogarList) {
    this.hogarList = hogarList;
  }

  @Override
  public String toString() {
    return ("{" + '"' + "total"  + '"' + ':' + getTotal() + ',' +
            '"' + "offset"  + '"' + ':' + getOffset() + ',' +
            '"' + "hogares" + '"' + ':' + getHogarList() +
            '}');
  }
}
