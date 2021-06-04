package apis;

import apis.dto.HogaresDTO;
import apis.dto.RequestEmailDTO;
import apis.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ConsumingRestApplication {

  private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ConsumingRestApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
    return args -> {
      Map<String, String> var1 = new HashMap<String, String>(3);
      var1.put("Authorization", "Bearer tokentest");

      // POST: Here we get the token
      TokenDTO tokenDTO = restTemplate.postForObject(
              "https://api.refugiosdds.com.ar/api/usuarios",
              new RequestEmailDTO("entregadetp@gmail.com"), TokenDTO.class, var1);
      log.info(tokenDTO.toString());

      Map<String, String> var2 = new HashMap<String, String>(3);
      var2.put("Authorization", "Bearer " + tokenDTO.getBearer_token());

      // GET: Here we get the list of "Hogares"
      HogaresDTO response = restTemplate.getForObject(
              "https://api.refugiosdds.com.ar/api/hogares", HogaresDTO.class, var2);
      log.info(response.toString());

    };
  }
}