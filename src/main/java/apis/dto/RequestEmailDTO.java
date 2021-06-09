package apis.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestEmailDTO {

  private String email;

  public RequestEmailDTO(@JsonProperty("email") String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "{" + '"' + "email" + '"' + ':' + '"' + getEmail() + '"' + '}';
  }


}
