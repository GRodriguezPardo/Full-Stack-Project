package controllers;

import mascotas.Caracteristica;
import repositorios.RepositorioDeMascotas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CaracteristicaController {

  private final static CaracteristicaController INSTANCE = new CaracteristicaController();

  private CaracteristicaController() {}

  public static CaracteristicaController instance (){
    return INSTANCE;
  }

  public Map<String, Object> obtenerSesion(Request request, Response response){
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", Objects.isNull(request.session().attribute("user_id")));
    return model;
  }

  public ModelAndView caracteristicas(Request request, Response response) {
    List<String> caracteristicas = RepositorioDeMascotas.instance().obtenerListado()
            .stream().flatMap(m -> m.getCaracteristicas().keySet().stream()).collect(Collectors.toList());

    return new ModelAndView(caracteristicas, "caracteristicas/caracteristicas.html.hbs");
  }

  public ModelAndView agregarCaracteristica(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "caracteristicas/caracteristicas.html.hbs");
  }

  public ModelAndView eliminarCaracteristica(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "caracteristicas/caracteristicas.html.hbs");
  }

}
