package services;

// Domain
import apis.HogaresDeTransitoAPI;
import apis.dto.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import exceptions.HogaresException;
import mascotas.Mascota;
import personas.Posicion;
import java.util.List;
import java.util.stream.Collectors;

// Jersey Library
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

// Javax Libary
import javax.ws.rs.core.MediaType;

public class HogaresService implements HogaresDeTransitoAPI {
  private final Client client;
  private static final String TOKEN = "eIlG30oTf8P7rBOtgWetcQH59wXUjcy4ZZGRSAf4Ba3t6xhh1Q2UmU6yXTPS";
  private static final String API_HOGARES = "https://api.refugiosdds.com.ar/api/";
  private static final String PATH_HOGARES = "hogares";
  private static final String PATH_USUARIOS = "usuarios";

  public HogaresService(){
    ClientConfig clientConfig = new DefaultClientConfig();
    clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    this.client = Client.create(clientConfig);
  }

  public HogaresDTO getHogares(String offset) throws HogaresException {
    return client
        .resource(API_HOGARES)
        .path(PATH_HOGARES)
        .queryParam("offset",offset)
        .header("Authorization","Bearer "+ TOKEN)
        .accept(MediaType.APPLICATION_JSON)
        .get(HogaresDTO.class);
  }

  @Override
  public List<HogarDTO> getHogarList(String offset) {
    return this.getHogares(offset).getHogares();
  }

  @Override
  public TokenDTO getToken (String email, String bearerToken) {
    WebResource recurso = this.client.resource(API_HOGARES).path(PATH_USUARIOS);
    WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
    String jsonBody = "{ \"body\": \"" + email + "\" }";
    return builder.post(TokenDTO.class, jsonBody);
  }

  public List<HogarDTO> getHogarMascota (Mascota mascota, Posicion ubicacion,int distanciaMaxima){
    //TODO: Debemos recorrer todos los offset
    return this.getHogarList("1")
        .stream()
        .filter(hogarDTO -> (hogarDTO.validarAdmision(mascota)
            && hogarDTO.getPoscion().distanciaA(ubicacion)<=distanciaMaxima))
        .collect(Collectors.toList());
  }

}
