package apis;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

  private String email;

  public Request(@JsonProperty("email") String email){
    this.email=email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


}
