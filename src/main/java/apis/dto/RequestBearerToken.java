package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestBearerToken {

  private String bearer;
  private int total;
  private int offset;

  public RequestBearerToken(String bearer, int total, int offset) {
    this.bearer = bearer;
    this.total = total;
    this.offset = offset;
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

  public String getBearer() {
    return bearer;
  }

  public void setBearer(String bearer) {
    this.bearer = bearer;
  }

  @Override
  public String toString() {
    return "{" +
            "total:" + getTotal() + '\'' +
            ", offset:" + getOffset() + '\'' +
            "'}'";
  }

}
