package controllers;

import repositorios.RepositorioDeRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotaController {
  private final static  MascotaController INSTANCE = new MascotaController();
  private final static RepositorioDeRescates mascotas = RepositorioDeRescates.getInstance();;

  private MascotaController(){
  }

  public static MascotaController instance (){
    return INSTANCE;
  }

  public ModelAndView encontradas(Request request, Response response) {
    return new ModelAndView(mascotas,"encontradas.html.hbs");
  }

  public ModelAndView registrar(Request request, Response response) {
    return new ModelAndView(mascotas,"registrar.html.hbs");
  }

  public ModelAndView perdidas(Request request, Response response) {
    return new ModelAndView(mascotas,"perdidas.html.hbs");
  }

  public ModelAndView mascota(Request request, Response response) {
    return new ModelAndView(mascotas,"mascota.html.hbs");
  }
}
