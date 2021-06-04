package apis;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("api.refugiosdds.com.ar/api")
@RestController
public class RefugioService implements RefugioAPI {

  @Override
  @PostMapping("/usuarios")
  public List<Map<String, Object>> postUsuarioMethod(@RequestBody Request request) {
    return Arrays.asList(new HashMap<String,Object>(){
      { put("email",request.getEmail());}
    });
  }

  @Override
  @GetMapping("/hogares")
  public List<Map<String, Object>> getHogaresMethod() {
    return Arrays.asList(new HashMap<String, Object>());
  }

}
