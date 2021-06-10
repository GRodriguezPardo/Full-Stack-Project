package services;

import apis.HogaresDeTransitoAPI;
import apis.dto.*;
import exceptions.HogaresException;
import mascotas.Especie;
import mascotas.Mascota;
import mascotas.Tamanio;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import personas.Posicion;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

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

  private boolean validate(Mascota mascota,Posicion ubicacion,int distanciaMaxima, HogarDTO hogar){

    //valido por disponibilidad
    if (hogar.getLugares_disponibles() == 0)
      return false;

    //valido por especie
    if (((hogar.getAdmisiones().getGato() && (mascota.getEspecie() != Especie.GATO)))
    || ((hogar.getAdmisiones().getPerro() && (mascota.getEspecie() != Especie.PERRO)))) return false;

    //valido por tamaño
    if ((mascota.getTamanio()== Tamanio.MEDIANO || mascota.getTamanio()== Tamanio.GRANDE)
      && !hogar.getPatio()) return false;

    //valido por ubicacion
    Posicion posicionHogar = new Posicion(hogar.getUbicacion().getLong(),hogar.getUbicacion().getLat());
    if ((posicionHogar.distanciaA(ubicacion)) > distanciaMaxima)
      return false;

    return true;
  }

  public List<HogarDTO> getHogarMascota (Mascota mascota, Posicion ubicacion,int distanciaMaxima){
    return this.getHogarList(1).stream().
        filter(x->validate(mascota,ubicacion,distanciaMaxima,x))
        .collect(Collectors.toList());
  }

}
