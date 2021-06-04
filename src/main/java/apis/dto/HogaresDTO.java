package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HogaresDTO {

  private int total;
  private int offset;
  private List<HogarDTO> hogarList;

  public HogaresDTO() {
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public List<HogarDTO> getHogarList() {
    return hogarList;
  }

  public void setHogarList(List<HogarDTO> hogarList) {
    this.hogarList = hogarList;
  }

  @Override
  public String toString() {
    return "{" +
            "total:" + getTotal() + '\'' +
            ", offset:" + getOffset() + '\'' +
            ", hogares:" + getHogarList() +
            "'}'";
  }
}
