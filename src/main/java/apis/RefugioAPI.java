package apis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface RefugioAPI {

  List<Map<String, Object>> postUsuarioMethod(Request request);
  List<Map<String, Object>> getHogaresMethod();
}
