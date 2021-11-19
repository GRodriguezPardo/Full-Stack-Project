package controllers;

import mascotas.PosibleCaracteristica;
import personas.Rescatista;
import repositorios.RepositorioDeCaracteristicas;
import repositorios.RepositorioDeMascotas;
import repositorios.RepositorioDeRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class CaracteristicaController {

  public Map<String, Object> obtenerSesion(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    model.put("sesioniniciada", Objects.isNull(request.session().attribute("user_id")));
    return model;
  }

  public ModelAndView caracteristicas(Request request, Response response) {
    List<PosibleCaracteristica> caracteristicas = RepositorioDeCaracteristicas.getInstance().getPosiblesCaracteristicas();

    Map<String, Object> model = new HashMap<>();
    model.put("caracteristicas", caracteristicas);

    return new ModelAndView(model, "caracteristicas/caracteristicas.html.hbs");
  }

  public ModelAndView agregarCaracteristica(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "caracteristicas/caracteristicas.html.hbs");
  }

  public ModelAndView eliminarCaracteristica(Request request, Response response) {
    return new ModelAndView(obtenerSesion(request, response), "caracteristicas/caracteristicas.html.hbs");
  }

}
