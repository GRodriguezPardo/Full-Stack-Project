package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HomeController {
  public ModelAndView index(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "home/home.html.hbs");
  }

  //TODO: Debemos recuperar la sesión del usuario, en caso de fallar
  // ir al login nuevamentes
  public Map<String, Object> obtenerSesion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", !Objects.isNull(request.session().attribute("user_id")));
    return model;
  }

  public ModelAndView error(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "home/error.html.hbs");
  }

  public ModelAndView ejemploClase(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "home/ejemploClase.html.hbs");
  }
}
