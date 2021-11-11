package controllers;

import mascotas.Caracteristica;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicaController {

  private final static CaracteristicaController INSTANCE = new CaracteristicaController();

  private CaracteristicaController(){
  }

  public static CaracteristicaController instance (){
    return INSTANCE;
  }

  public ModelAndView caracteristicas(Request request, Response response) {
    return new ModelAndView(null,"caracteristicas.html.hbs");
  }

  public ModelAndView agregarCaracteristica(Request request, Response response) {
    return new ModelAndView(null,"agregarCaracteristica.html.hbs");
  }

  public ModelAndView eliminarCaracteristica(Request request, Response response) {
    return new ModelAndView(null,"eliminarCaracteristica.html.hbs");
  }

}
