package apis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenDTO {
  private String bearer_token;

  public TokenDTO(){
  }

  public String getBearer_token() {
    return bearer_token;
  }

  public void setBearer_token(String bearer_token) {
    this.bearer_token = bearer_token;
  }

  @Override
  public  String toString(){
    return "{" +
          "bearer_token:" + getBearer_token() +
          "'}'";
  }

}
