package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
  public ModelAndView index(Request request, Response response) {
    return new ModelAndView(obtenerSesion(), "home/home.html.hbs");
  }

  //TODO: Debemos recuperar la sesión del usuario, en caso de fallar
  // ir al login nuevamentes
  public String obtenerSesion(){
    return "Hola Mundo";
  }

  public ModelAndView error(Request request, Response response) {
    return new ModelAndView(obtenerSesion(), "home/error.html.hbs");
  }

  public ModelAndView ejemploClase(Request request, Response response) {
    return new ModelAndView(obtenerSesion(), "home/ejemploClase.html.hbs");
  }
}
