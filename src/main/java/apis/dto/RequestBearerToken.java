package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestBearerToken {

  private String bearer;
  private int total;
  private String offset;

  public RequestBearerToken(String bearer, int total, String offset) {
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

  public String getOffset() {
    return offset;
  }

  public void setOffset(String offset) {
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
