package apis.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailDTO {

  private String email;

  public EmailDTO(@JsonProperty("email") String email) {
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
