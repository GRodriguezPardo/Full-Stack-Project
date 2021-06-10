package services;

import apis.HogaresDeTransitoAPI;
import apis.dto.HogarDTO;
import apis.dto.HogaresDTO;
import apis.dto.EmailDTO;
import apis.dto.TokenDTO;
import exceptions.HogaresException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class HogaresDeTransitoService implements HogaresDeTransitoAPI {

  private final RestTemplate restTemplate;
  private static final String token = "eIlG30oTf8P7rBOtgWetcQH59wXUjcy4ZZGRSAf4Ba3t6xhh1Q2UmU6yXTPS";
  private static final String url_hogares = "https://api.refugiosdds.com.ar/api/hogares?offset=";
  private static final String url_usuarios = "https://api.refugiosdds.com.ar/api/usuarios";
  private static HttpHeaders headers;

  public HogaresDeTransitoService(RestTemplate restTemplate){
    this.restTemplate = restTemplate;
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    System.out.println(this.getHogarList(1));
  }

  @Override
  public HogaresDTO getHogares(int offset) throws HogaresException {
    headers.setBearerAuth(token);
    HttpEntity<String> entity = new HttpEntity <> (headers);
    return restTemplate.exchange(url_hogares + offset, HttpMethod.GET, entity, HogaresDTO.class).getBody();
  }

  @Override
  public List<HogarDTO> getHogarList(int offset) {
    return this.getHogares(offset).getHogarList();
  }

  @Override
  public TokenDTO getToken (String email, String bearerToken) {
    headers.setBearerAuth(bearerToken);
    return restTemplate.postForObject(url_usuarios, new EmailDTO(email), TokenDTO.class, headers);
  }

}
