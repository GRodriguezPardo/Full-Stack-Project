package apis.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDTO {

  private String email;

  public RequestDTO(@JsonProperty("email") String email){
    this.email=email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


}
